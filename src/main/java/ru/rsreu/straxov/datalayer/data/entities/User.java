package ru.rsreu.straxov.datalayer.data.entities;

import java.util.Set;

/**
 * Represents a user in the system with attributes related to login, role, authorization, and blocking status.
 * This class includes methods for getting and setting user details and statuses.
 */
public class User {

    /** The login name of the user */
    private String userLogin;

    /** The role ID of the user */
    private int userRoleId;

    /** The ID of the user */
    private int userId;

    /** The password of the user */
    private String userPassword;

    /** The blocked status of the user */
    private Boolean userBlockedStatus;

    /** The authorization status of the user */
    private Boolean userAuthorizedStatus;

    /**
     * Constructs a new User with the specified user ID and role ID.
     * This constructor is typically used when initializing a user with basic identity information.
     *
     * @param userId the unique identifier of the user
     * @param roleId the role ID of the user
     */
    public User(int userId, int roleId) {
        this.userId = userId;
        this.userRoleId = roleId;
    }

    /**
     * Constructs a new User with the specified login, password, and role ID.
     *
     * @param userLogin the login of the user
     * @param userPassword the password of the user
     * @param userRoleId the role ID of the user
     */
    public User(String userLogin, String userPassword, int userRoleId) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRoleId = userRoleId;
    }

    /**
     * Constructs a new User with the specified user details including blocked and authorization status.
     *
     * @param userId the unique identifier of the user
     * @param userLogin the login of the user
     * @param userPassword the password of the user
     * @param userBlockedStatus the blocked status of the user
     * @param userAuthorizedStatus the authorization status of the user
     * @param userRoleId the role ID of the user
     */
    public User(int userId, String userLogin, String userPassword, Boolean userBlockedStatus,
                Boolean userAuthorizedStatus, int userRoleId) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRoleId = userRoleId;
        this.userBlockedStatus = userBlockedStatus;
        this.userAuthorizedStatus = userAuthorizedStatus;
        this.userId = userId;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Returns the login name of the user.
     *
     * @return the user login
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Sets the login name of the user.
     *
     * @param userLogin the user login to set
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * Returns the role ID of the user.
     *
     * @return the role ID
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * Sets the role ID of the user.
     *
     * @param userRoleId the role ID to set
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * Returns the blocked status of the user.
     *
     * @return true if the user is blocked, false otherwise
     */
    public Boolean isUserBlockedStatus() {
        return userBlockedStatus;
    }

    /**
     * Sets the blocked status of the user.
     *
     * @param userBlockedStatus the blocked status to set
     */
    public void setUserBlockedStatus(Boolean userBlockedStatus) {
        this.userBlockedStatus = userBlockedStatus;
    }

    /**
     * Returns the authorization status of the user.
     *
     * @return true if the user is authorized, false otherwise
     */
    public Boolean isUserAuthorizedStatus() {
        return userAuthorizedStatus;
    }

    /**
     * Sets the authorization status of the user.
     *
     * @param userAuthorizedStatus the authorization status to set
     */
    public void setUserAuthorizedStatus(Boolean userAuthorizedStatus) {
        this.userAuthorizedStatus = userAuthorizedStatus;
    }

    /**
     * Returns the password of the user.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the password of the user.
     *
     * @param userPassword the user password to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Returns the blocked status of the user.
     *
     * @return the user blocked status
     */
    public Boolean getUserBlockedStatus() {
        return userBlockedStatus;
    }

    /**
     * Returns the authorized status of the user.
     *
     * @return the user authorized status
     */
    public Boolean getUserAuthorizedStatus() {
        return userAuthorizedStatus;
    }
}
