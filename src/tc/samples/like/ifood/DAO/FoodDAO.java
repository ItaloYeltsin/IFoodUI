package tc.samples.like.ifood.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class FoodDAO extends DAO{
	private PreparedStatement getFoodSt;
	private static RestaurantDAO rDAO = new RestaurantDAO(); 
	
	public FoodDAO() {
		try {
			getFoodSt = util.prepareStatement("select * from food where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Food getFood(int id) {
		try {
			getFoodSt.setInt(1, id);
			ResultSet r = getFoodSt.executeQuery();
			if(!r.next()) return null;
			return resultToFood(r);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Food> getFood() {
		try {
			ResultSet r = getFoodSt.executeQuery("select * from food");
			if(r.getFetchSize() == 0) return null;
			
			ArrayList<Food> foods = new ArrayList<Food>();
			while(r.next()) {
				foods.add(resultToFood(r));
			}
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Food> getFoodByRestaurant(int restID) {
		try {
			Statement st = util.con().createStatement();
			ResultSet r = st.executeQuery("select * from food where rest_id="+restID);
			
			ArrayList<Food> foods = new ArrayList<Food>();
			while(r.next()) {
				foods.add(resultToFood(r));
			}
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Food resultToFood(ResultSet r) throws SQLException, ImageException {
		Food food = new Food();
		food.id = r.getInt("id");
		food.name = r.getString("name");
		food.description = r.getString("description");
		food.price = r.getDouble("price");
		return food;
	}
}
