package tc.samples.like.ifood.ui;

import tc.samples.like.ifood.IFoodConstants;
import tc.samples.like.ifood.IFoodUI;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PressListener;
import totalcross.ui.image.Image;

public class BottomBar extends Container implements PressListener {

	IFoodTabbedContainer content;
	Button search;
	Button discover;
	Button order;
	Button me;
	Button[] btns = new Button[4];
	int last = -1;
	private static final int SEARCH_INDEX = 0;
	private static final int DISCOVER_INDEX = 1;
	private static final int ORDER_INDEX = 2;
	private static final int ME_INDEX = 4;

	public void initUI() {
		setBorderStyle(BORDER_TOP);
		setForeColor(IFoodConstants.BOTTOMBAR_TOP_LINE);
		IFoodContainer ifc = new IFoodContainer();
		add(search = ifc.createButton(ifc.SEARCH), LEFT, CENTER, PARENTSIZE + 25, PREFERRED);
		add(discover = ifc.createButton(ifc.DISCOVER), AFTER, SAME, PARENTSIZE + 25, PREFERRED);
		add(order = ifc.createButton(ifc.ORDER), AFTER, SAME, PARENTSIZE + 25, PREFERRED);
		add(me = ifc.createButton(ifc.USER), AFTER, SAME, PARENTSIZE + 25, PREFERRED);

		// Config button;
		config(search, 0);
		config(discover, 1);
		config(order, 2);
		config(me, 3);

	}

	public IFoodTabbedContainer getContent() {
		return content;
	}

	public void setContent(IFoodTabbedContainer content) {
		this.content = content;
	}

	public void controlPressed(ControlEvent e) {
		setPressed(((Button) e.target).appId, true);
	}

	public void setPressed(int cur) {
		setPressed(cur, false);
	}

	private void setPressed(int cur, boolean post) {
		if (cur != last) {
			if (last >= 0) {
				btns[last].press(false);
				btns[last].setForeColor(IFoodConstants.BOTTOMBAR_TEXT_COLOR);
			}
			btns[cur].press(true);
			btns[cur].setForeColor(IFoodConstants.BOTTOMBAR_TEXT_ACTIVE_COLOR);
			last = cur;
			postPressedEvent();
		}
	}

	private void config(Button b, int i) {
		b.shiftOnPress = false;
		b.isSticky = true;
		b.addPressListener(this);
		b.appId = i;
		btns[i] = b;
	}

	public int getSelectedButtonPosition() {
		return last;
	}

}
