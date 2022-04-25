package petsToGo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Age {
	private GregorianCalendar dob;
	private Calendar today;
	
	public Age() {
		super();
	}
	
	public Age(GregorianCalendar dob) {
		super();
		this.dob = dob;
	}
	
	/**
	 * returns the DOB as a Calendar object.
	 * @return
	 */
	public GregorianCalendar getDob() {
		return dob;
	}
	
	/**
	 * returns age as string of both age in years and months.
	 * @return
	 */
	public String returnAge() {
		Calendar today = Calendar.getInstance();
		int age = (today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)) * 12;
		int months = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
		String ageString = "";
		int monthsAge = age + months;
		ageString = ageString + monthsAge + "months";
/*		ageString = ageString + age + " years and ";
		ageString = ageString + months + " months old";*/
		return ageString;
	}

	public void setDob(GregorianCalendar dob) {
		this.dob = dob;
	}

	public Calendar getToday() {
		today = Calendar.getInstance();
		return today;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Age) {
			Age age = (Age) obj;
			if (age.dob.equals(dob)) {
				return true;
			}
		}
		return false;
	}
}