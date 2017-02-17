package tc.samples.like.ifood.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import tc.samples.like.ifood.model.Evaluation;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Vm;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class RestaurantDAO extends DAO{
	private PreparedStatement getRestaurantSt;
	private static FoodDAO fDAO = new FoodDAO();
	public RestaurantDAO() {
		try {
			getRestaurantSt = util.prepareStatement("select * from restaurant where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Restaurant getRestaurant(int id) {
		try {
			getRestaurantSt.setInt(1, id);
			ResultSet r = getRestaurantSt.executeQuery();
			if(!r.next()) return null;
			return resultToRestaurant(r);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Restaurant> getRestaurant() {
		try {
			Statement st = util.con().createStatement();
			ResultSet r = st.executeQuery("select * from restaurant");
			if(r == null) return null;
			
			ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
			Vm.debug(r.getRow()+"");
			while(r.next()) {
				rests.add(resultToRestaurant(r));
			}
			return rests;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Restaurant resultToRestaurant(ResultSet r) throws SQLException, ImageException {
		Restaurant rest = new Restaurant();
		rest.id = r.getInt("id");
		rest.category = r.getString("category");
		rest.closeHour = r.getTime("closeHour");
		rest.description = r.getString("description");
		rest.higherToDeliv = r.getTime("higherToDeliv");
		byte [] bytes = r.getBytes("image");
		rest.image = bytes == null ? null : new Image(bytes);
		rest.lat = r.getDouble("lat");
		rest.lng = r.getDouble("lng");
		rest.lowerToDeliv = r.getTime("lowerToDeliv");
		rest.name = r.getString("name");
		rest.openHour = r.getTime("openHour");
		return rest;
	}
	
	private ArrayList<Evaluation> getEvaluations(Restaurant restaurant) throws SQLException {
		ArrayList<Evaluation> evaluations = new ArrayList<>();
		Statement st = util.con().createStatement();
		ResultSet r = st.executeQuery("select * from evaluation where rest_id="+restaurant.id);
		UserDAO uDAO = new UserDAO();
		while(r.next()) {
			//addEvaluationSt = util.prepareStatement("insert into evaluation (description, rate, rest_id,"
			//		+ "user_id) values (?,?,?,?)");
			Evaluation e = new Evaluation();
			e.description = r.getString("description");
			e.rate = r.getInt("rate");
			e.user = uDAO.getUser(r.getInt("user_id"));
			e.restaurant = restaurant;
			evaluations.add(e);
			
		}
		return evaluations;
	}
	
	
	
}
