package tc.samples.like.ifood.ui;

import totalcross.io.IOException;
import totalcross.sys.Vm;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class IFoodImages {
	private IFoodImages iFoodImages;
	public static Image search, searchPressed, discover, discoverPressed, 
		order, orderPressed, user, userPressed;
	public static Image star, haroldo, chinaInBox, gendai, burggraf;
	public static Image hamburguer, japanese, chinese, pizza;
	public static Image back, heart, heartEmpty;
	public static void loadImages(int fmH) {
		int top = fmH*3/2;
	      try {
			search = new Image("img/search.png").smoothScaledFixedAspectRatio(fmH,true);
			searchPressed = new Image("img/search_pressed.png").smoothScaledFixedAspectRatio(fmH,true);
			discover = new Image("img/discover.png").smoothScaledFixedAspectRatio(fmH,true);
			discoverPressed = new Image("img/discover_pressed.png").smoothScaledFixedAspectRatio(fmH,true);
			order = new Image("img/order.png").smoothScaledFixedAspectRatio(fmH,true);
			orderPressed = new Image("img/order_pressed.png").smoothScaledFixedAspectRatio(fmH,true);
			user = new Image("img/user.png").smoothScaledFixedAspectRatio(fmH,true);
			userPressed = new Image("img/user_pressed.png").smoothScaledFixedAspectRatio(fmH,true);
			star = new Image("img/star.png").smoothScaledFixedAspectRatio(fmH,true);
			haroldo = new Image("img/haroldo.png");
			chinaInBox = new Image("img/china_in_box.png");
			gendai = new Image("img/gendai.png");
			burggraf = new Image("img/burggraf.png");
			hamburguer = new Image("img/hamburguer_category.png").smoothScaledFixedAspectRatio(fmH,true);
			japanese = new Image("img/japanese_category.png").smoothScaledFixedAspectRatio(fmH,true);
			chinese = new Image("img/chinese_category.png").smoothScaledFixedAspectRatio(fmH,true);
			pizza = new Image("img/pizza_category.png").smoothScaledFixedAspectRatio(fmH,true);
			back = new Image("img/back.png").smoothScaledFixedAspectRatio(fmH,true);
			heart = new Image("img/heart.png").smoothScaledFixedAspectRatio(fmH,true);
			heartEmpty = new Image("img/heart_empty.png").smoothScaledFixedAspectRatio(fmH,true);
		} catch (ImageException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
