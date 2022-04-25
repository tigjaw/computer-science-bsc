package Trialing;

import java.sql.Date;
import java.util.Calendar;

public class dates {
	
	public static void main(String[]args) {
		sqlDate();
	}
	
	public static void sqlDate() {
		/*Calendar cal = Calendar.getInstance();
		// set Date portion to January 1, 1970
	    cal.set( cal.YEAR, 1970 );
	    cal.set( cal.MONTH, cal.JANUARY );
	    cal.set( cal.DATE, 1 );
	    cal.set( cal.HOUR_OF_DAY, 0 );
	    cal.set( cal.MINUTE, 0 );
	    cal.set( cal.SECOND, 0 );
	    cal.set( cal.MILLISECOND, 0 );
	    
	    java.sql.Date date = 
	       new java.sql.Date( cal.getTime().getTime() );
	       */
		java.sql.Date date = java.sql.Date.valueOf("2010-01-31");
		System.out.println(date);
	}
	
}