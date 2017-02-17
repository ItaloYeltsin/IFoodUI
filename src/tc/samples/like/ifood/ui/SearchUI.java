package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class SearchUI extends ScrollContainer{
	
	RestaurantListUI rList;
	MenuContainer menu;
	MakeOrderUI makeOrder;
	RestaurantListTopBar topBar = new RestaurantListTopBar();
	public SearchUI() {
		super(false, true);
		rList = new RestaurantListUI(this);	
		menu = new MenuContainer(this);
		makeOrder = new MakeOrderUI(this);
	}
	
	@Override public void initUI() {
		add(topBar, LEFT, TOP, FILL, fmH*4);
		add(rList, LEFT, AFTER, FILL, FILL);
	}
	
	public void changeToRestaurantMenu(RestaurantContainer rc) {
		removeAll();
		scrollToPage(0);
		add(menu, LEFT, TOP, FILL, WILL_RESIZE);
		menu.mountUI(rc.rest);
		rc.resizeHeight();
	}
	
	public void changeToRestaurantMenu() {
		removeAll();
		scrollToPage(0);
		add(menu, LEFT, TOP, FILL, WILL_RESIZE);
	}
	
	public void changeToMain() {
		removeAll();
		scrollToPage(0);
		add(topBar, LEFT, TOP, FILL, fmH*4);
		add(rList, LEFT, AFTER, FILL, FILL);
	}

	public void changeToConfirmOrder(Food f, Restaurant r) {
		scrollToPage(0);
		removeAll();
		add(makeOrder, LEFT, TOP, FILL, FILL);
		makeOrder.mountUI(f, r);
	}

}
