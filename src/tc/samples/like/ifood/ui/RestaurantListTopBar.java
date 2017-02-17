package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.gfx.Color;

public class RestaurantListTopBar extends Container {
	
	public Button search;
	
	@Override
	public void initUI() {
		setBackColor(IFoodConstants.TOPBAR_COLOR);
		
		//Label
		Label label = new Label(IFoodUI.user.address);
		label.setFont(font.adjustedBy(-4, true));
		label.align = CENTER;
		label.setForeColor(Color.WHITE);
		add(label, CENTER, TOP, PARENTSIZE+80, PREFERRED);
		
		search = new Button("Busca restaurante, pratos ou culinária", 
				IFoodImages.search, RIGHT, 1);
		search.setBorder(BORDER_ROUNDED);
		search.setBackColor(Color.WHITE);
		search.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		search.setFont(font.adjustedBy(-4));
		search.imageHeightFactor = 50;
		add(search, CENTER, AFTER+50, PARENTSIZE+80, fmH*4/3);
	}
	
}
