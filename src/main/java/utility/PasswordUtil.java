package utility;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	    public static String hashPassword(String password) {
	        return BCrypt.hashpw(password, BCrypt.gensalt(12));
	    }

	    public static boolean checkPassword(String password, String hashedPassword) {
	        return BCrypt.checkpw(password, hashedPassword);
	    }
}
