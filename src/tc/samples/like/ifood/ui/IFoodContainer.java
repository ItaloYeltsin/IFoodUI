package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.IFoodConstants;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.font.Font;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class IFoodContainer extends Container {

	public static final int SEARCH = 0;
	public static final int DISCOVER = 1;
	public static final int ORDER = 2;
	public static final int USER = 3;

	public Button createButton(int type) {
		Button b;
		if (type == SEARCH) {
			b = new Button("Buscar", IFoodImages.search, Control.BOTTOM, fmH);
			b.pressedImage = IFoodImages.searchPressed;
		} else if (type == DISCOVER) {
			b = new Button("Descobrir", IFoodImages.discover, Control.BOTTOM, fmH);
			b.pressedImage = IFoodImages.discoverPressed;
		} else if (type == ORDER) {
			b = new Button("Pedidos", IFoodImages.order, Control.BOTTOM, fmH);
			b.pressedImage = IFoodImages.orderPressed;
		} else if (type == USER) {
			b = new Button("Eu", IFoodImages.user, Control.BOTTOM, fmH);
			b.pressedImage = IFoodImages.userPressed;
		} else {
			return null;
		}
		b.setFont(Font.getFont(true, fmH * 8 / 10));
		b.setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);		
		b.setBorder(Button.BORDER_NONE);
		return b;
	}

}
