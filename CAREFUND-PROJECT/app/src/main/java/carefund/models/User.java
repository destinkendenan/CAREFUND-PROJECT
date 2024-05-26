package main.java.carefund.models;

public class User {
    private String username;
    private String email;
    private String password; // Store the hashed password
    private String name; // Optional: User's full name
    private String address; // Optional: User's address
    private String phoneNumber; // Optional: User's phone number

    // Constructor (username, email, and password required)
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // New Constructor for username and email only (other fields will be null)
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        // Other fields (password, name, address, phoneNumber) will be null by default
    }

    // Overloaded constructor for all fields (if needed)
    public User(String username, String email, String password, String name, String address, String phoneNumber) {
        this(username, email, password); // Call the primary constructor
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters (You might want to restrict some of these, like username)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ... (Other methods as needed, e.g., for hashing passwords, validation, etc.)
}
