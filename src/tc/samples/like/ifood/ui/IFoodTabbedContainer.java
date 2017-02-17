package tc.samples.like.ifood.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import tc.samples.like.ifood.IFoodConstants;
import totalcross.sys.Vm;
import totalcross.ui.Container;
import totalcross.ui.TabbedContainer;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.DragEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PenEvent;
import totalcross.ui.event.PenListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.image.Image;

public class IFoodTabbedContainer extends TabbedContainer{

	private static final String [] names = {"1", "2", "3", "4"};
	private int currentTab = 0;
	BottomBar bottomBar;
	
	public IFoodTabbedContainer(BottomBar bottomBar) {
		super(names);
		setType(TABS_NONE);
		this.bottomBar = bottomBar;
		bottomBar.setContent(this);
		
	}
	@Override
	public void initUI() {
		setBackColor(IFoodConstants.GENERAL_BACKGROUND_COLOR);
	}


	
	



}
