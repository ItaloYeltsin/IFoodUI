package tc.samples.like.ifood;

import java.util.Random;

import tc.samples.like.ifood.DAO.UserDAO;
import tc.samples.like.ifood.db.IFoodDB;
import tc.samples.like.ifood.model.User;
import tc.samples.like.ifood.ui.BottomBar;
import tc.samples.like.ifood.ui.IFoodImages;
import tc.samples.like.ifood.ui.IFoodTabbedContainer;
import tc.samples.like.ifood.ui.OrderContainer;
import tc.samples.like.ifood.ui.OrdersUI;
import tc.samples.like.ifood.ui.SearchUI;
import tc.samples.like.ifood.ui.UserUI;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.MainWindow;
import totalcross.ui.TabbedContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

public class IFoodUI extends MainWindow {
	static {
		Settings.companyContact = "registro@totalcross.com";
		Settings.companyInfo = "TotalCross";
		// Settings.appPackagePublisher =
		// "53F995CF-1FB5-4EC3-84DD-A694BE4CFD1A";
		// Settings.appPackageIdentifier = "1748TotalCross.TotalCrossAPI";
		Settings.iosCFBundleIdentifier = "com.totalcross.ifoodui";
		Settings.windowSize = Settings.WINDOWSIZE_480X640;
	}

	BottomBar bottomBar;
	IFoodTabbedContainer content;
	
	public static double lat = new Random().nextInt(31);
	public static double lng = new Random().nextInt(31);
	
	public static User user;
	
	public IFoodUI() {
		Settings.uiAdjustmentsBasedOnFontHeight = true;
		setUIStyle(Settings.Android);
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
		IFoodImages.loadImages(fmH);
		IFoodDB.getInstance();
		UserDAO uDAO = new UserDAO();
		user = uDAO.getUser(1);
		user.favorites = uDAO.restrieveFavorites(user);
		Vm.debug(user.favorites.size()+"");
		user.orders = uDAO.retrieveOrder(user);
		
	}

	public void initUI() {
		add(bottomBar = new BottomBar(), LEFT, BOTTOM, FILL, fmH * 5 / 2);
		add(content = new IFoodTabbedContainer(bottomBar), LEFT, TOP, FILL, FIT);
		content.setContainer(0, new SearchUI());
		content.setContainer(2, new OrdersUI());
		content.setContainer(3, new UserUI());
		bottomBar.setPressed(0);
	}

	@Override
	public void onEvent(Event e) {
		switch (e.type) {
		case ControlEvent.PRESSED:
			if (e.target == content) {
				bottomBar.setPressed(content.getActiveTab());
			} else if (e.target == bottomBar) {
				content.setActiveTab(bottomBar.getSelectedButtonPosition());
			}
		}
	}

}
