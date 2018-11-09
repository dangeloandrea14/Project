package Model;

public class User {

    private static String email;
    private static String name;
    private static String profession;
    private static String qualification;
    private static String role;
    private static String password;
    private static int download;
    private static int ID;
    
    public static void setDonwload(int download) {
    	User.download= download;
    }
    public static void SetID(int ID) {
    	User.ID = ID;
        }
    public static void SetRole(String role) {
    	User.role = role;
        }

    public static void setEmail(String email) {
	User.email = email;
    }

    public static void setName(String name) {
	User.name = name;
    }

    public static void setProfession(String profession) {
	User.profession = profession;
    }

    public static void setQualification(String qualification) {
	User.qualification = qualification;
    }
    
    public static void SetPassword(String password) {
    	User.password = password;
     }
    
    public static String getEmail() {
	return User.email;
    }

    public static String getName() {
	return User.name;
    }

    public static String getProfession() {
	return User.profession;
    }

    public static String getQualification() {
	return User.qualification;
    }
   
    public static String getRole() {
    	return User.role;
        }

    public static String getPassword() {
    	return User.password;
        }
    public static int getDownload() {
    	return User.download;
    }
	public static int getID() {
		return User.ID;
		
	}
}
