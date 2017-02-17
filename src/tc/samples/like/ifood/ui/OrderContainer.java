package tc.samples.like.ifood.ui;



import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.model.Order;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.ImageException;

public class OrderContainer extends Container{
	
	private Order order;
	public OrderContainer(Order order) {
		this.order = order;
	}	
	
	public void initUI() {
		this.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		this.setBorderStyle(BORDER_TOP);
		this.setForeColor(IFoodConstants.BOTTOMBAR_TOP_LINE);
		
		Label name = new Label(order.restaurant.name);
		name.setFont(font.asBold());
		name.setForeColor(Color.BLACK);
		Label date = new Label(order.date.getDay()+" de "+order.date.getMonthName(
				order.date.getMonth())+" de "+order.date.getYear());
		date.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		
		try {
			add(new ImageControl(order.restaurant.image.smoothScaledFixedAspectRatio(fmH*2, true))
					, LEFT, CENTER, PARENTSIZE+20, PREFERRED);
		} catch (ImageException e) {
			e.printStackTrace();
		}
		
		
		add(name, AFTER, TOP, PARENTSIZE+80, fmH);
		add(date, SAME, AFTER, PARENTSIZE+80, fmH);
		Label food = new Label(order.food.name);
		food.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		add(food, SAME, AFTER, PARENTSIZE+80, fmH);
		
	}
}
