package tc.samples.like.ifood.ui;

import java.text.DecimalFormat;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import tc.samples.like.ifood.Utils;
import tc.samples.like.ifood.model.Food;
import tc.samples.like.ifood.model.Restaurant;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.event.PenListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.ImageException;

public class FoodContainer extends Container{
	public Food food;
	public Glass glass;
	

	public FoodContainer(Food food) {
		this.food = food;
		glass = new Glass(this);
	}
	
	@Override
	public void addPenListener(PenListener listener) {
		glass.addPenListener(listener);
	}
	
	@Override
	public void initUI() {
		this.setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		this.setBorderStyle(BORDER_TOP);
		this.setForeColor(IFoodConstants.BOTTOMBAR_TOP_LINE);
		
		Label name = new Label(food.name);
		name.setFont(font.asBold());
		name.setForeColor(Color.BLACK);
		Label description = new Label(food.description);
		description.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
		/*try {
			add(new ImageControl(rest.image.smoothScaledFixedAspectRatio(fmH*2, true))
					, LEFT, CENTER, PARENTSIZE+20, PREFERRED);
		} catch (ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String price = new DecimalFormat("##.##").format(food.price);
		Label priceLab = new Label("R$ "+price);
		priceLab.setForeColor(Color.GREEN);
		name.vAlign = description.vAlign = TOP;
		description.autoSplit = name.autoSplit = true;		
		
		add(name, LEFT, TOP, PARENTSIZE+60, fmH*3);
		add(description, SAME, AFTER, PARENTSIZE+60, fmH*4);
		add(priceLab, RIGHT, TOP, PARENTSIZE+20, PREFERRED);
		
		add(glass, LEFT, TOP, PARENTSIZE+100, PARENTSIZE+100);
		
	}
}
