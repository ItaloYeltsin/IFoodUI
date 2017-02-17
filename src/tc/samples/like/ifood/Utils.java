package tc.samples.like.ifood;

import java.util.Comparator;

import totalcross.sys.Time;
import totalcross.sys.Vm;

public class Utils {
	public static double distance(double x1, double y1, double x2, double y2) {
		double x = Math.pow(x1-x2, 2);
		double y = Math.pow(y1-y2, 2);
		return Math.pow(x+y, 0.5);
	}

	static Comparator<Time> p = new Comparator<Time>() {

		@Override
		public int compare(Time o1, Time o2) {
			if(o1.hour > o2.hour) {
				return 1;
			}else if(o1.hour == o2.hour) {
				if(o1.minute > o2.minute)
					return 1;
				else if(o1.minute == o2.minute)
					return 0;
				else
					return-1;
			}else {
				return -1;
			}
		}
	};
		
	public static boolean isInOpenInterval(Time time, Time openHour, Time closeHour) {
		int comparison = p.compare(openHour, closeHour);
		if(comparison > 0) {
			if(p.compare(time, openHour) > 0 && p.compare(time, closeHour) > 0 ||
					p.compare(time, openHour) < 0 && p.compare(time, closeHour) < 0 )
				return true;
			else
				return false;
		}else if (comparison == 0) {
			return true; // 24 hours
		}else {
			if(p.compare(time, openHour) > 0 && p.compare(time, closeHour) < 0)
				return true;
			else
				return false;
		}
	}
	
	
	
}
