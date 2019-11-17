package model.account;

import model.Model;

/**
 * This contains information about the admin
 */
public class Admin implements Model {

    /**
     * username of the admin
     */
    private String username;
    /**
     * password of the admin
     */
    private String password;

    /**
     * This constructor is used to initialized the username and password for admin
     *
     * @param username The username of the admin
     * @param password The password of the admin
     */
    public Admin(String username, String password) {

        this.username = username;
        this.password = password;
    }

    /**
     * This method returns the username of the admin
     *
     * @return the username of the admin
     */
    public String getUsername() {

        return username;
    }

    /**This method returns the password of the admin
     * @return the password of the admin
     */
    public String getPassword() {

        return password;
    }
}
