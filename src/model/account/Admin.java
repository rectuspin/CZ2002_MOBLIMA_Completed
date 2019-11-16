package model.account;

import model.Model;

public class Admin implements Model {

    private String username;

    private String password;

    public Admin(String username, String password) {
        /**This constructor is used to initialized the username and password for admin
         * @param username  The username of the admin
         * @param password  The password of the admin
         */
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        /**This method returns the username of the admin
         * @return the username of the admin
         */
        return username;
    }

    public String getPassword() {
        /**This method returns the password of the admin
         * @return the password of the admin
         */
        return password;
    }


}
