package tc.samples.like.ifood.ui;


import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.model.Order;
import totalcross.ui.Bar;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.Event;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.gfx.Color;

public class OrdersUI extends ScrollContainer{
	
	int lastOrdersSize = IFoodUI.user.orders.size();
	
	public OrdersUI() {
		super(false, true);
	}
	
	@Override
	public void initUI() {
		Bar topBar;
		add(topBar = new Bar("Pedidos"), LEFT, TOP, FILL, fmH*4/2);
		topBar.setBackColor(IFoodConstants.TOPBAR_COLOR);
		topBar.setForeColor(Color.WHITE);
		topBar.titleAlign = CENTER;
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		
		for (Order order : IFoodUI.user.orders) {
			add(new OrderContainer(order), LEFT, AFTER, PARENTSIZE+96, fmH*4);
		}
	}
	
	@Override
	public void _onEvent(Event e) {
		super._onEvent(e);
		if(e.type == MouseEvent.MOUSE_IN) { //update
			if(IFoodUI.user.orders.size() != lastOrdersSize) {
				lastOrdersSize = IFoodUI.user.orders.size();
				removeAll();
				initUI();
			}
		}
		if(e.type == MouseEvent.MOUSE_OUT) { //update
			if(IFoodUI.user.orders.size() != lastOrdersSize) {
				scrollToPage(0);
			}
		}
		
	}
}
