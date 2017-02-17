package tc.samples.like.ifood.ui;



import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.Utils;
import tc.samples.like.ifood.DAO.FoodDAO;
import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sys.Time;
import totalcross.sys.Vm;
import totalcross.ui.Bar;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Label;
import totalcross.ui.Toast;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class MenuContainer extends Container implements PressListener, PenListener{
	
	SearchUI searchUI;
	Container content;
	Bar topBar;
	public boolean isClosed = false;
	public Restaurant restaurant;
	public boolean wasDragged = false;
	public MenuContainer(SearchUI searchUI) {
		this.searchUI = searchUI;
		content = new Container();
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
	}
	
	
	public void mountUI(Restaurant r){
		removeAll();	
		restaurant = r;
		if(r.food == null) {
			r.food = new FoodDAO().getFoodByRestaurant(r.id);
		}
		add(topBar = new Bar(r.name), LEFT, TOP, FILL, fmH*4/2);
		topBar.setBackColor(IFoodConstants.TOPBAR_COLOR);
		topBar.setForeColor(Color.WHITE);
		topBar.addButton(IFoodImages.back, false);
		if(IFoodUI.user.isFavorite(r)) {
			topBar.addButton(IFoodImages.heart, true);
		}else {
			topBar.addButton(IFoodImages.heartEmpty, true);
		}
		topBar.addPressListener(this);
		add(new RestaurantContainer(r), LEFT, AFTER, FILL, fmH*3);
		
		if(!Utils.isInOpenInterval(new Time(), r.openHour, r.closeHour)) {
			String openHour = r.openHour.toString();
			// range from zero to length-1 minus the last 3 digits, which are :00, that is, milliseconds
			openHour = openHour.substring(0, (openHour.length())-3); 
			add(new OpenAdvertise(openHour), CENTER, AFTER, PARENTSIZE+80, PREFERRED);
			isClosed = true;
		}else {
			isClosed = false;
		}
		//add(content, LEFT, CENTER, FILL, FILL);
		//Add food containers
		for (Food food : r.food) {
			Container c;
			add(c = new FoodContainer(food), LEFT, AFTER, PARENTSIZE, fmH*7);
			c.addPenListener(this);
		}
		
	}
	
	private static class OpenAdvertise extends Container{
		String openTime;
		
		public OpenAdvertise(String openTime) {
			this.openTime = openTime;
		}
		
		public void initUI() {
			setBackColor(0x313131);
			setBorderStyle(BORDER_ROUNDED);
			Label msg = new Label("Fechado - Abre às "+openTime);
			msg.setForeColor(Color.WHITE);
			add(msg, CENTER, CENTER, PREFERRED, PREFERRED);
		}
		
	}

	@Override
	public void controlPressed(ControlEvent e) {
		if(e.target == topBar) {
			if(topBar.getSelectedIndex() == 1) {
				searchUI.changeToMain();
			}else if(topBar.getSelectedIndex() == 2) {
				if(IFoodUI.user.isFavorite(restaurant)) {
					topBar.removeButton(2);
					topBar.addButton(IFoodImages.heartEmpty, true);
					IFoodUI.user.removeFavorite(restaurant);
					topBar.updateScreen();
				}else {
					topBar.removeButton(2);
					topBar.addButton(IFoodImages.heart, true);
					IFoodUI.user.addFavorite(restaurant);
					Vm.debug("1");
					topBar.repaintNow();
				}
			}
		}
	}


	@Override
	public void penDown(PenEvent e) {
		
	}


	@Override
	public void penUp(PenEvent e) {
		if(wasDragged) {
			wasDragged = false;
			return;
		}
		
		if(isClosed) {
			Toast.show("Restaurante está fechado!", 10);
			return;
		}
		Glass glass = (Glass)e.target;
		
		Control c = (Control)glass.getControl();
		
		if(FoodContainer.class.isInstance(c)) {
			searchUI.changeToConfirmOrder(((FoodContainer)c).food, restaurant);				
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
