package tc.samples.like.ifood.ui;

import java.text.DecimalFormat;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.Utils;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.PenListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.ImageException;

public class RestaurantContainer extends Container{
	public Restaurant rest;
	public Glass glass;

	public RestaurantContainer(Restaurant rest) {
		//super(false);
		this.rest = rest;
		glass = new Glass(this);
	}
	
	@Override
	public void addPenListener(PenListener listener) {
		super.addPenListener(listener);
		glass.addPenListener(listener);
	}
	
	@Override
	public void initUI() {
		this.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		this.setBorderStyle(BORDER_TOP);
		this.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		
		Label name = new Label(rest.name);
		name.setFont(font.asBold());
		name.setForeColor(Color.BLACK);
		
		double distance = Utils.distance(IFoodUI.lat, IFoodUI.lng, rest.lat, rest.lng);
		String d = new DecimalFormat("##.#").format(distance)+"km";
		Label distAndET = 
				new Label(d+" \u25E6 "+rest.lowerToDeliv.minute+"-"+rest.higherToDeliv.minute+"m");
		distAndET.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		
		try {
			add(new ImageControl(rest.image.smoothScaledFixedAspectRatio(fmH*2, true))
					, LEFT, CENTER, PARENTSIZE+20, PREFERRED);
		} catch (ImageException e) {
			e.printStackTrace();
		}
		add(name, AFTER, TOP, PARENTSIZE+60, fmH);
		add(distAndET, SAME, AFTER, PARENTSIZE+60, fmH*2/3);	
		add(new ImageControl(IFoodImages.star), RIGHT, TOP, PARENTSIZE+10, PREFERRED);
		add(glass, LEFT, TOP, PARENTSIZE+100, PARENTSIZE+100);
	}
	
	
}
