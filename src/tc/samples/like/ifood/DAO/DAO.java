package tc.samples.like.ifood.DAO;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sys.Settings;
import totalcross.sys.Vm;

public abstract class DAO {
	protected static SQLiteUtil util;
	
	DAO() {
		try
	      {
	        if(util == null) { 
	        	util = new SQLiteUtil(Settings.appPath,"ifood.db");
	         	Vm.debug(util.fullPath);
	        }
	      }
	      catch (Throwable t)
	      {
	         throw new RuntimeException(t);
	      }
	}
}
