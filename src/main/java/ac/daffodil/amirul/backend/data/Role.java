package ac.daffodil.amirul.backend.data;

public class Role {
	public static final String BARISTA = "user";
	public static final String BAKER = "user";
	public static final String ADMIN = "admin";
	public static final String USER = "user";

	private Role() {
		// Static methods and fields only
	}

	public static String[] getAllRoles() {
		return new String[] { ADMIN, USER };
	}

}
