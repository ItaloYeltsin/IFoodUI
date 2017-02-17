package tc.samples.like.ifood.model;

import java.util.ArrayList;

import totalcross.sys.Time;
import totalcross.ui.image.Image;

public class Restaurant {
	public int id;
	public String name;
	public ArrayList<Food> food;
	public double lng;
	public double lat;
	public String description;
	public Image image;
	public String category;
	public Time openHour;
	public Time closeHour;
	public Time lowerToDeliv;
	public Time higherToDeliv;
	public ArrayList<Evaluation> evaluations;
	
	public boolean userlAlreadyEvaluated(User u) {
		for (Evaluation evaluation : evaluations) {
			if(evaluation.user.id == u.id) {
				return true;
			}
		}
		return false;
	}
	
	
}
