package tc.samples.like.ifood.ui;

import java.util.ArrayList;

import javax.swing.DebugGraphics;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.DAO.RestaurantDAO;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.ListContainer;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Scrollable;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.FocusListener;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.event.WindowListener;

public class RestaurantListUI extends Container implements PenListener{
	RestaurantDAO rDAO = new RestaurantDAO();
	SearchUI searchUI;
	boolean wasDragged = false;
	public RestaurantListUI(SearchUI searchUI) {
		this.searchUI = searchUI;
	}
		

	@Override
	public void initUI() {
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		ArrayList<Restaurant> rests = rDAO.getRestaurant();
		add(new RestaurantCategoryBar(), CENTER, AFTER, PARENTSIZE+100, fmH*2);
		for (int i = 0; i < rests.size(); i++) {
			RestaurantContainer rc = new RestaurantContainer(rests.get(i));
			//rc.glass.addPenListener(this);
			rc.addPenListener(this);
			add(rc, CENTER, AFTER, PARENTSIZE+96, fmH*3);
		}
		
		//add(listOfRestaurants, LEFT, TOP, FILL, FILL);
	}

	@Override
	public void penDown(PenEvent e) {

		
		
	}

	@Override
	public void penUp(PenEvent e) {
		
		if(Glass.class.isInstance(e.target) && !wasDragged) {
			Glass glass = (Glass)e.target;
			Control c = ((RestaurantContainer)glass.getControl());
			if(RestaurantContainer.class.isInstance(c)) {
				searchUI.changeToRestaurantMenu((RestaurantContainer)c);
			}
		}else {
			wasDragged = false;
		}
	}

	@Override
	public void penDrag(DragEvent e) {
		wasDragged = true;
	}

	@Override
	public void penDragStart(DragEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penDragEnd(DragEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
