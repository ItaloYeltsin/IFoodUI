package tc.samples.like.ifood.model;

import java.util.ArrayList;

import tc.samples.like.ifood.DAO.UserDAO;
import totalcross.ui.image.Image;

public class User {
	public int id;
	public Image photo;
	public String name;
	public String email;
	public String address;
	public ArrayList<Order> orders;
	public ArrayList<Restaurant> favorites;
	
	public void addFavorite(Restaurant restaurant) {
		favorites.add(restaurant);
		new UserDAO().addFavorite(this, restaurant);
	}
	
	public void removeFavorite(Restaurant restaurant) {
		for (Restaurant r : favorites) {
			if(restaurant.id == r.id) {
				favorites.remove(r);
				break;
			}
		}
		new UserDAO().removeFavorite(this, restaurant);
	}
	
	public boolean isFavorite(Restaurant restaurant) {
		for (Restaurant r : favorites) {
			if(r.id == restaurant.id) {
				return true;
			}
		}
		return false;
	}
	
	public void addOrders(Restaurant restaurant, Food food) {
		orders.add(new UserDAO().addOrder(this, restaurant, food));	
		
	}
	
	
	
	
	
}
