package tc.samples.like.ifood.ui;

import java.text.DecimalFormat;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.Utils;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.PenListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.ImageException;

public class RestaurantPortraitContainer extends Container {
	Restaurant rest;
	
	public RestaurantPortraitContainer(Restaurant rest) {
		this.rest = rest;
	}
	
	
	
	@Override
	public void initUI() {
		this.setBackColor(IFoodConstants.BOTTOMBAR_TOP_LINE);
		this.setBorderStyle(BORDER_ROUNDED);
		this.setForeColor(IFoodConstants.BOTTOMBAR_TOP_LINE);
		
		Label name = new Label(rest.name);
		name.setFont(font.asBold());
		name.setForeColor(Color.BLACK);
		
		Label ET = new Label(rest.lowerToDeliv.minute+"-"+rest.higherToDeliv.minute+"m");
		Label category = new Label(rest.category);

		ET.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		category.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		
		try {
			ImageControl ic = new ImageControl(rest.image.smoothScaledFixedAspectRatio(fmH*4, true));
			ic.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
			ic.centerImage = true;
			add(ic, CENTER, TOP, PARENTSIZE+96, fmH*4);
			
		} catch (ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(name, LEFT, AFTER, PARENTSIZE+96, fmH*3/2);
		add(ET, SAME, BOTTOM, PARENTSIZE+96, fmH);
		add(category, LEFT, BEFORE, PARENTSIZE+96, fmH);
	}

}
