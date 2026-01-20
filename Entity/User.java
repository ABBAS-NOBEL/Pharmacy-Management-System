package Entity;

import java.io.*;

public class User {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String gender;
    public User() {}
    
    public User(String name, String email, String password, String phone,String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
		this.gender=gender;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
	
	public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public void saveToFile() {
        try {
            File file = new File("./Data/users.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            
            FileWriter fwriter = new FileWriter(file, true);
            fwriter.write("========================================\n");
            fwriter.write("Name: " + name + "\n");
            fwriter.write("Email: " + email + "\n");
            fwriter.write("Password: " + password + "\n");
            fwriter.write("Phone: " + phone + "\n");
			fwriter.write("Gender: " + gender + "\n");
            fwriter.write("========================================\n");
            
            fwriter.flush();
            fwriter.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    //checks if user exists
    public static boolean checkUserExists(String email, String password) {
        try {
            File file = new File("./Data/users.txt");
            if (!file.exists()) {
                return false;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean found = false;
            boolean checkingEmail = false;
            
            while ((line = reader.readLine()) != null) {
                if (line.contains("Email: " + email)) {
                    checkingEmail = true;
                }
                if (checkingEmail && line.contains("Password: " + password)) {
                    found = true;
                    break;
                }
                if (line.contains("========================================")) {
                    checkingEmail = false;
                }
            }
            reader.close();
            return found;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
}