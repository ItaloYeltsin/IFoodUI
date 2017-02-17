package tc.samples.like.ifood.ui;

import java.util.ArrayList;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.model.Order;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sys.Vm;
import totalcross.ui.Bar;
import totalcross.ui.Container;
import totalcross.ui.FlowContainer;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.Event;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.gfx.Color;

public class UserUI extends Container{
	ArrayList<Restaurant> favorites = IFoodUI.user.favorites;
	int lastFavoritesSize = favorites.size();
	Container content;	
	FlowContainer favHolder;
	@Override
	public void initUI() {
		Bar topBar;
		add(topBar = new Bar(IFoodUI.user.name), LEFT, TOP, FILL, fmH*4/2);
		topBar.setBackColor(IFoodConstants.TOPBAR_COLOR);
		topBar.setForeColor(Color.WHITE);
		topBar.titleAlign = CENTER;
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		
		Label fav = new Label("Favoritos");
		fav.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		fav.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		add(fav, LEFT+50, AFTER, fmH*10, fmH*3);
		
		
		content = new ScrollContainer(true, false);
		content.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		add(content, LEFT, AFTER, FILL, FILL);
		
		
		if(favorites.size() > 0)
			content.add(new RestaurantPortraitContainer(favorites.get(0)), 
					SAME, AFTER, fmH*7, fmH*10);
		
		for (int i = 1; i < favorites.size(); i++) {
			
			content.add(new RestaurantPortraitContainer(favorites.get(i)), 
					AFTER+50, SAME, fmH*7, fmH*10);
			
		}
		
		
		
	}
	
	public int getPreferredHeight() {
		return fmH*6;
	}
	
	@Override
	public void _onEvent(Event e) {
		super._onEvent(e);
		if(e.type == MouseEvent.MOUSE_IN) { //update
			if(favorites.size() != lastFavoritesSize) {
				lastFavoritesSize = favorites.size();
				removeAll();
				initUI();
			}
		}
		
	}

}
