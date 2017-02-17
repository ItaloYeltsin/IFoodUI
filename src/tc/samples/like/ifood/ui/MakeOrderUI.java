package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.DAO.UserDAO;
import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.ui.Bar;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Toast;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class MakeOrderUI extends Container implements PressListener{

	private Bar topBar;
	private SearchUI sUI;
	private Button confirmOrder;
	private Food food;
	private Restaurant restaurant;
	private UserDAO uDAO = new UserDAO();
	
	MakeOrderUI(SearchUI sUI) {
		this.sUI = sUI;
	}
	
	public void mountUI(Food food, Restaurant restaurant) {
		this.food = food;
		this.restaurant = restaurant;
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		add(topBar = new Bar(food.name), LEFT, TOP, FILL, fmH*2);
		topBar.setBackColor(IFoodConstants.TOPBAR_COLOR);
		topBar.setForeColor(Color.WHITE);
		topBar.addButton(IFoodImages.back, false);
		topBar.addPressListener(this);
		
		add(new FoodContainer(food), LEFT, AFTER, FILL, fmH*7);
		confirmOrder = new Button("Confirmar Pedido");
		confirmOrder.setBackColor(IFoodConstants.TOPBAR_COLOR);
		confirmOrder.addPressListener(this);
		add(confirmOrder, LEFT, AFTER, FILL, fmH*2);
		confirmOrder.setForeColor(Color.WHITE);
	}

	@Override
	public void controlPressed(ControlEvent e) {
		if(e.target == topBar) {
			sUI.changeToRestaurantMenu();
		} else if(e.target == confirmOrder) {
			uDAO.addOrder(IFoodUI.user, restaurant, food);
			Toast.show("Pedido Confirmado", 10);
			sUI.changeToMain();
			
		}
	}
}
