package app.com.example.foodie.foodieandroid.Model;

import app.com.example.foodie.foodieandroid.Utility.Location;
import app.com.example.foodie.foodieandroid.Utility.UserSettings;

/**
 * Created by Jennifer on 11/11/15.
 */
public abstract class User {
    private int user_id;
    private String name;
    private Image profile_img;
    private String username;
    private String password;
    private UserSettings settings;
    private Location location;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Image profile_img) {
        this.profile_img = profile_img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}