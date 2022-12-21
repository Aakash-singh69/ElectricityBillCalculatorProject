package javaFiles;

import java.util.HashMap;

public class Admin {
	private static HashMap<Integer, String> adminDetails = new HashMap<Integer, String>();

	static {
		adminDetails.put(12345, "electricity12");
		adminDetails.put(67890, "electricity23");
	}
	
	public static boolean validateAdmin(int adminId, String password) {
		if (adminDetails.containsKey(adminId)) {
			if (adminDetails.get(adminId).equalsIgnoreCase(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
