package tc.samples.like.ifood.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Order;
import tc.samples.like.ifood.model.Restaurant;
import tc.samples.like.ifood.model.User;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sys.Vm;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.util.Date;

public class UserDAO extends DAO{
	
	PreparedStatement getUserSt; 
	PreparedStatement addFav;
	PreparedStatement retrieveFav;
	PreparedStatement removeFav;
	PreparedStatement retrieveOrders;
	PreparedStatement addOrder;
	
	public UserDAO() {
		super();
		try {
			getUserSt = util.prepareStatement("select * from user where id = ?");
			addFav = util.prepareStatement("insert into favorite (rest_id, user_id) values (?,?)");
			retrieveFav = util.prepareStatement("select rest_id from favorite where user_id=?");
			removeFav = util.prepareStatement("DELETE from favorite where user_id=? and rest_id=?");
			retrieveOrders = util.prepareStatement("select * from orders where user_id = ?");
			addOrder = util.prepareStatement("insert into orders (rest_id, user_id, food_id, date"
					+ ") values (?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(int id) {
		try {
			getUserSt.setInt(1, id);
			ResultSet r = getUserSt.executeQuery();
			if(!r.next()) return null;
			User u = new User();
			u.id = id;
			u.email = r.getString("email");
			u.name = r.getString("name");
			byte [] bytes = r.getBytes("photo");
			u.photo = bytes == null ? null : new Image(bytes);
			u.address = r.getString("address");
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ImageException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public void addFavorite(User user, Restaurant rest) {
		try {
			addFav.setInt(1, rest.id);
			addFav.setInt(2, user.id);
			addFav.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeFavorite(User user, Restaurant restaurant) {
		try {
			removeFav.setInt(1, user.id);
			removeFav.setInt(2, restaurant.id);
			removeFav.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Restaurant> restrieveFavorites(User user) {
		ArrayList<Restaurant> favorites = new ArrayList<>();
		try {
			RestaurantDAO rDAO = new RestaurantDAO();
			retrieveFav.setInt(1, user.id);
			ResultSet r = retrieveFav.executeQuery();
			while(r.next()) {
				favorites.add(rDAO.getRestaurant(r.getInt("rest_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return favorites;
	}
	
	public ArrayList<Order> retrieveOrder(User user) {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			RestaurantDAO rDAO = new RestaurantDAO();
			FoodDAO fDAO = new FoodDAO();
			retrieveOrders.setInt(1, user.id);
			ResultSet r = retrieveOrders.executeQuery();
			while(r.next()) {
				Order o = new Order();
				o.user = user;
				o.food = fDAO.getFood(r.getInt("food_id"));
				o.restaurant = rDAO.getRestaurant(r.getInt("rest_id"));
				o.id = r.getInt("id");
				o.date = r.getDate("date");
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public Order addOrder(User user, Restaurant restaurant, Food food) {
		try {
			Vm.debug(restaurant+"");
			Order order = new Order();
			order.date = new Date();
			order.user = user;
			order.restaurant = restaurant;
			order.food = food;
			
			addOrder.setInt(1, restaurant.id);
			addOrder.setInt(2, user.id);
			addOrder.setInt(3, food.id);
			addOrder.setDate(4, order.date);
			addOrder.executeUpdate();
			
			return order;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
