package mtp.util.member;

import java.util.List;
public class EL_Utility {

	public EL_Utility() {
	}

	public static List<String> getMonth() {
		try {
			return new MemberUtil().getMonth();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getYear() {
		try {
			return new MemberUtil().getYear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
