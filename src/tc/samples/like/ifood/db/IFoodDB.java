package tc.samples.like.ifood.db;

import java.sql.SQLException;

import tc.sample.like.fb.FBUtils;
import tc.samples.like.ifood.ui.IFoodImages;
import totalcross.db.sqlite.SQLiteUtil;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.sql.PreparedStatement;
import totalcross.sql.Statement;
import totalcross.sys.Settings;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.ui.image.ImageException;

public class IFoodDB {
	
	private static IFoodDB db = new IFoodDB();
	private SQLiteUtil util;
	private PreparedStatement addUserSt, addRestSt, addFoodSt, addOrderSt, addEvaluationSt,
		addFavoriteSt, addMenuSt;
	private IFoodDB() {
		try
	      {
	         util = new SQLiteUtil(Settings.appPath,"ifood.db");
	         createTables();
	      }
	      catch (Throwable t)
	      {
	         throw new RuntimeException(t);
	      }
	}
	
	public static IFoodDB getInstance() {
		return db;
	}
	
	private void createTables() throws SQLException
	   {
	      Statement st = util.con().createStatement();
	      /*util.con().createStatement();
	      st.executeUpdate("drop table if exists user");
	      st.executeUpdate("drop table if exists restaurant");
	      st.executeUpdate("drop table if exists food");
	      st.executeUpdate("drop table if exists evaluation");
	      st.executeUpdate("drop table if exists favorite");
	      st.executeUpdate("drop table if exists orders");
	      */
	      	         
	      //table user
	      st.execute("create table if not exists user (id integer NOT NULL, name varchar, email varchar,"
	      		+ "lat float, lng float, photo blob, address varchar, PRIMARY KEY (ID))");
	      //table restaurant 
	      st.execute("create table if not exists restaurant (id integer NOT NULL, name varchar, "
	      		+ "description varchar, image blob, address varchar, openHour time, closeHour time,"
	      		+ " lowerToDeliv time, higherToDeliv time, category varchar, lat float, lng float,"
	      		+ " PRIMARY KEY (id))");
	      //table food
	      st.execute("create table if not exists food (id integer NOT NULL, name varchar,"
	      		+ "description varchar, price float, photo blob, REST_ID integer, FOREIGN KEY (REST_ID)"
	    		+"REFERENCES restaurant (id), PRIMARY KEY (id))");
	      //table evaluation
	      st.execute("create table if not exists evaluation (id integer NOT NULL, description varchar,"
		      		+ "rate integer, REST_ID integer, USER_ID integer, FOREIGN KEY (REST_ID) "
		      		+ "REFERENCES restaurant (id), FOREIGN KEY (USER_ID) REFERENCES user (id),"
		      		+ " PRIMARY KEY (id))");
	      //table favorite
	      st.execute("create table if not exists favorite (id integer NOT NULL,"
		      		+ "REST_ID integer, USER_ID integer, FOREIGN KEY (REST_ID) "
		      		+ "REFERENCES restaurant (id), FOREIGN KEY (USER_ID) REFERENCES user (id),"
		      		+ " PRIMARY KEY (id))");
	      
	      //table order
	      st.execute("create table if not exists orders (id integer NOT NULL, date date," 
		      		+ "FOOD_ID integer, REST_ID integer, USER_ID integer, qtd integer,"
		      		+ " FOREIGN KEY (REST_ID) "
		      		+ "REFERENCES restaurant (id), FOREIGN KEY (USER_ID) REFERENCES user (id), "
		      		+ "FOREIGN KEY (FOOD_ID) REFERENCES food (id), PRIMARY KEY (id))");
	      if(!st.executeQuery("select * from user").next()) {
	    	  try {
				populate();
			} catch (ImageException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      
	      st.close();
	      
	   }
	
	public void populate() throws SQLException, ImageException, IOException {
		//add Users
		addUserSt = util.prepareStatement("insert into user (name, email, lat, lng, address) values (?,?,?,?,?)");
		
		addUserSt.setString(1, "Mann Whitney");
		addUserSt.setString(2, "mannw@utest.com");
		addUserSt.setInt(3, 15);
		addUserSt.setInt(4, 12);
		addUserSt.setString(5, "R. Dedé Brasil, 1707");
		addUserSt.executeUpdate();
		
		addUserSt.setString(1, "Tannenbaum ");
		addUserSt.setString(2, "author@so.com");
		addUserSt.setDouble(3, 15.0);
		addUserSt.setDouble(4, 12.0);
		addUserSt.executeUpdate();
		
		addUserSt.setString(1, "Mark Harman");
		addUserSt.setString(2, "harman@ucl.com");
		addUserSt.setDouble(3, 15.0);
		addUserSt.setDouble(4, 12.0);
		addUserSt.executeUpdate();
		
		addUserSt.setString(1, "Kalyanmoy Deb");
		addUserSt.setString(2, "kdeb@egr.msu.edu");
		addUserSt.setDouble(3, 15.0);
		addUserSt.setDouble(4, 12.0);
		addUserSt.executeUpdate();
		
		//add Restaurants
		addRestSt = util.prepareStatement("insert into restaurant (name, description, openHour,"
				+ " closeHour, lowerToDeliv, higherToDeliv, category, lat, lng, image)"
				+ " values (?,?,?,?,?,?,?,?,?,?)");
		
		addRestSt.setString(1, "China In Box");
		addRestSt.setString(2, "China In Box, o maior e melhor delivery de comida chinesa entrega na caixinha"
				+ "mais conhecida do Brasil. Pratos tradicionais como yakisoba, frango, etc...");
		addRestSt.setTime(3, new Time(0, 01, 1, 10, 00, 00, 00));
		addRestSt.setTime(4, new Time(0, 01, 1, 23, 00, 00, 00));
		
		addRestSt.setTime(5, new Time(0, 01, 1, 0, 45, 00, 00));
		addRestSt.setTime(6, new Time(0, 01, 1, 0, 60, 00, 00));
		addRestSt.setString(7, "Chinesa");
		addRestSt.setDouble(8, 20.0);
		addRestSt.setDouble(9, 30.0);
		ByteArrayStream bas = new ByteArrayStream(20*1024);
		IFoodImages.chinaInBox.createPng(bas);
		addRestSt.setBytes(10, bas.toByteArray());
		addRestSt.executeUpdate();
		
		addRestSt.setString(1, "Gendai Aldeota");
		addRestSt.setString(2, "Gendai é a maior e melhor franquia de culinária japonesa no Brasil. Agora"
				+ "está perto de você! Com sushis, sashimis, etc..");
		addRestSt.setTime(3, new Time(0, 01, 1, 00, 00, 00, 00));
		addRestSt.setTime(4, new Time(0, 01, 1, 8, 00, 00, 00));
		
		addRestSt.setTime(5, new Time(0, 01, 1, 0, 15, 00, 00));
		addRestSt.setTime(6, new Time(0, 01, 1, 0, 45, 00, 00));
		addRestSt.setString(7, "Japonesa");
		addRestSt.setDouble(8, 0.0);
		addRestSt.setDouble(9, 30.0);
		bas = new ByteArrayStream(20*1024);
		IFoodImages.gendai.createPng(bas);
		addRestSt.setBytes(10, bas.toByteArray());
		addRestSt.executeUpdate();
		
		addRestSt.setString(1, "Haroldo Pizzaria");
		addRestSt.setString(2, "Haroldo Pizza, a pizza mais deliciosa do mundo.");
		addRestSt.setTime(3, new Time(0, 01, 1, 0, 00, 00, 00));
		addRestSt.setTime(4, new Time(0, 01, 1, 0, 00, 00, 00));
		
		addRestSt.setTime(5, new Time(0, 01, 1, 0, 30, 00, 00));
		addRestSt.setTime(6, new Time(0, 01, 1, 0, 60, 00, 00));
		addRestSt.setString(7, "Pizzaria");
		addRestSt.setDouble(8, 15.0);
		addRestSt.setDouble(9, 15.0);
		bas = new ByteArrayStream(20*1024);
		IFoodImages.haroldo.createPng(bas);
		addRestSt.setBytes(9, bas.toByteArray());
		addRestSt.executeUpdate();
		
		addRestSt.setString(1, "BURGGRAF");
		addRestSt.setString(2, "Peça na mais nova Hamburgueria da região. Carnes artesanais e molhos caseiros"
				+ "são o nosso grande diferencial. Peça e se surpreeenda.");
		addRestSt.setTime(3, new Time(0, 01, 1, 18, 00, 00, 00));
		addRestSt.setTime(4, new Time(0, 01, 1, 22, 00, 00, 00));
		
		addRestSt.setTime(5, new Time(0, 01, 1, 0, 45, 00, 00));
		addRestSt.setTime(6, new Time(0, 01, 1, 0, 60, 00, 00));
		addRestSt.setString(7, "Lanches");
		addRestSt.setDouble(8, 15.0);
		addRestSt.setDouble(9, 15.0);
		bas = new ByteArrayStream(20*1024);
		IFoodImages.burggraf.createPng(bas);
		addRestSt.setBytes(10, bas.toByteArray());
		addRestSt.executeUpdate();
				
		addEvaluationSt = util.prepareStatement("insert into evaluation (description, rate, rest_id,"
				+ "user_id) values (?,?,?,?)");
		
		addEvaluationSt.setString(1, "Muito Bom!");
		addEvaluationSt.setInt(2, 5);
		addEvaluationSt.setInt(3, 1);
		addEvaluationSt.setInt(4, 3);
		addEvaluationSt.executeUpdate();
		
		addEvaluationSt.setString(1, "Horrivel");
		addEvaluationSt.setInt(2, 1);
		addEvaluationSt.setInt(3, 1);
		addEvaluationSt.setInt(4, 4);
		addEvaluationSt.executeUpdate();
		
		addEvaluationSt.setString(1, "Bom!");
		addEvaluationSt.setInt(2, 3);
		addEvaluationSt.setInt(3, 2);
		addEvaluationSt.setInt(4, 2);
		addEvaluationSt.executeUpdate();
		
		addEvaluationSt.setString(1, "Muito Bom!");
		addEvaluationSt.setInt(2, 4);
		addEvaluationSt.setInt(3, 1);
		addEvaluationSt.setInt(4, 1);
		addEvaluationSt.executeUpdate();
		
		addEvaluationSt.setString(1, "Muito Bom!");
		addEvaluationSt.setInt(2, 5);
		addEvaluationSt.setInt(3, 1);
		addEvaluationSt.setInt(4, 3);
		addEvaluationSt.executeUpdate();
		
		
		
		addFavoriteSt = util.prepareStatement("insert into favorite (rest_id, user_id) values (?,?)");
		
		addFavoriteSt.setInt(1, 1);
		addFavoriteSt.setInt(1, 2);
		addFavoriteSt.setInt(1, 4);
		
		
		addFoodSt = util.prepareStatement("insert into food (name, description, price, rest_id) values"
				+ " (?,?,?,?)");
		
		addFoodSt.setString(1, "YAKISSOBA CLÁSSICO PEQUENO + Rolinho");
		addFoodSt.setString(2, "Macarrão com carne, frango, legumes etc...");
		addFoodSt.setDouble(3, 35.50);
		addFoodSt.setInt(4, 1);
		addFoodSt.executeUpdate();
		
		addFoodSt.setString(1, "BOWL FRANGO CRISPY +  ROLINHO PRIMAVERA");
		addFoodSt.setString(2, "Yakimeshi e pedaços de frango empanado com farinha crocante"
				+ "salpicado com cebolinha e molho oriental");
		addFoodSt.setDouble(3, 22.60);
		addFoodSt.setInt(4, 1);
		addFoodSt.executeUpdate();
		
		addFoodSt.setString(1, "CARNE COM LEGUMES PEQUENO EXECUTIVO + ROLINHO PRIMAVERA");
		addFoodSt.setString(2, "Carne Fatiada com legumes temperados ao molho de soja.");
		addFoodSt.setDouble(3, 32.70);
		addFoodSt.setInt(4, 1);
		addFoodSt.executeUpdate();

		addFoodSt.setString(1, "Teste");
		addFoodSt.setString(2, "Teste");
		addFoodSt.setDouble(3, 32.70);
		addFoodSt.setInt(4, 2);
		addFoodSt.executeUpdate();
		
		addOrderSt = util.prepareStatement("insert into orders (food_id, rest_id, user_id,"
				+ " date, qtd) values"
				+ " (?,?,?,?,?)");
		
		
		
	}
}
