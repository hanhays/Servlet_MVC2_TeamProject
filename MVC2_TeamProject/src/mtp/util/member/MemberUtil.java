package mtp.util.member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemberUtil {

	public MemberUtil() {
		// TODO Auto-generated constructor stub
	}

//	public int getAgeFromBirthday(Date birthday) {
//	    Calendar birth = new GregorianCalendar();
//	    Calendar today = new GregorianCalendar();
//	    birth.setTime(birthday);
//	    today.setTime(new Date());
//
//	    int factor = 0;
//	    if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
//	        factor = -1;
//	    }
//	    return today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
//	}

	public int getAge(String year) throws Exception {
		int age = Calendar.getInstance().get(Calendar.YEAR) - strCastingint(year);//만나이
//		if (strCastingint(month) < 3) {
//			return age + 1;
//		} 빠른연생 구하는 로직
		return age + 1;
	}

	public List<String> getYear() throws Exception {
		int year = Calendar.getInstance().get(Calendar.YEAR);//현재 year
		List<String> years = new ArrayList<String>();
		for (int i = year; i > year - 100; i--) {
			years.add(intCastionStr(i));
		}
		return years;
	}

	public List<String> getMonth() throws Exception {
		List<String> month = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			month.add(intCastionStr(i));
		}
		return month;
	}

	private int strCastingint(String values) throws Exception {
		return Integer.parseInt(values);
	}

	private String intCastionStr(int values) throws Exception {
		String value = String.valueOf(values);
		return value.length() < 2 ? "0" + value : value;
	}
}
