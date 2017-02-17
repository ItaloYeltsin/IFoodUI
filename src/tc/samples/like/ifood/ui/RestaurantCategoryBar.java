package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.IFoodConstants;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class RestaurantCategoryBar extends Container {
	
	public Button japanese, chinese, lanches, pizza;
	
	public void initUI() {
		setBackColor(IFoodConstants.TOPBAR_COLOR);
		add(japanese = new Button("Japonesa"), LEFT, CENTER, PARENTSIZE+25, PREFERRED);
		configButton(japanese);
		add(chinese = new Button("Chinesa"), AFTER, CENTER, PARENTSIZE+25, PREFERRED);
		configButton(chinese);
		add(lanches = new Button("Lanches"), AFTER, CENTER, PARENTSIZE+25, PREFERRED);
		configButton(lanches);
		add(pizza = new Button("Pizza"), AFTER, CENTER, PARENTSIZE+25, PREFERRED);
		configButton(pizza);
	}
	
	public void configButton(Button b) {
		b.shiftOnPress = false;
		b.isSticky = false;
		b.setFont(Font.getFont(true, fmH * 8 / 10));
		b.setForeColor(Color.WHITE);		
		b.setBorder(Button.BORDER_NONE);
	}
}
