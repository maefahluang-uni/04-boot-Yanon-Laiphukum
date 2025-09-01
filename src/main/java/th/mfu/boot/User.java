package th.mfu.boot;

public class User {
    private String username;
    private String displayname;
    private String email;

    public User() {} // จำเป็นสำหรับ JSON

    public User(String username, String displayname, String email) {
        this.username = username;
        this.displayname = displayname;
        this.email = email;
    }

    // Getters / Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDisplayname() { return displayname; }
    public void setDisplayname(String displayname) { this.displayname = displayname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
