package Common;

public class GlobalContants {
    // Chua những thông tin dùng chung cho cả framework:
    // Url/ Username/ Password
	// Database
	// Server Url: dev, testing, staging, product
	// Environment
	// Timeout: Short/ long
	// download/ upload folder
	// relative project path
	// OS Name
	// ...
	
	public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
	// <parameter name="userUrl" value="https://demo.nopcommerce.com/" />
	public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/login";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final String DEV_ADMIN_USERNAME = "admin@yourstore.com";
	public static final String DEV_ADMIN_PASSWORD = "admin";
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + "/uploadFiles/";
	public static final String DOWNLOAD_PATH = RELATIVE_PROJECT_PATH + "/downloadFiles/";
	
	
	
	
	
}
