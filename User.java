import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String role;
    private String email;
    private String fullName;

    public User(String username, String password, String role, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    // Save user to file
    public void saveToFile(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            
            File userFile = new File(folder, "users.txt");
            FileWriter writer = new FileWriter(userFile, true);
            
            String userData = username + "|" + password + "|" + role + "|" + email + "|" + fullName + "\n";
            writer.write(userData);
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    // Load all users from file
    public static List<User> loadUsersFromFile(String folderPath) {
        List<User> users = new ArrayList<>();
        try {
            File userFile = new File(folderPath, "users.txt");
            if (userFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(userFile));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split("\\|");
                    if (userData.length == 5) {
                        User user = new User(userData[0], userData[1], userData[2], userData[3], userData[4]);
                        users.add(user);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // Check if username already exists
    public static boolean usernameExists(String username, String folderPath) {
        List<User> users = loadUsersFromFile(folderPath);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}