package tc.samples.like.ifood.ui;

import totalcross.ui.Container;
import totalcross.ui.Control;

public class Glass extends Container{
	
	private Control c;
	
	public Glass(Control c) {
		alphaValue = 0;
		this.c = c;
	}
	
	public Control getControl(){return c;}
}
