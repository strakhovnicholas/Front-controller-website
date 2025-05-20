package ru.rsreu.straxov.datalayer.data.daointerfaces;

import ru.rsreu.straxov.datalayer.data.entities.User;

import java.util.List;

/**
 * Interface for performing data access operations related to users in the system.
 * Provides methods for managing user accounts, including creating, updating, deleting users,
 * and managing user roles, authorization, and block statuses.
 */
public interface UserDAO {

    /**
     * Returns a list of all users along with their roles, filtered for users with a "moderator" role.
     *
     * @return a list of users with the "moderator" role and their corresponding roles
     */
    public List<User> getAllUsersAndRolesModer();

    /**
     * Returns a list of all users along with their roles, filtered for users with an "admin" role.
     *
     * @return a list of users with the "admin" role and their corresponding roles
     */
    public List<User> getAllUsersAndRolesAdmin();

    /**
     * Finds a user by their login and password.
     * This method is typically used for user authentication.
     *
     * @param login the login of the user to be found
     * @param password the password of the user to be found
     * @return the user associated with the given login and password, or null if no such user exists
     */
    public User findUser(String login, String password);

    /**
     * Creates a new user in the system and adds it to the database.
     *
     * @param user the user to be created
     */
    public void createUser(User user);

    /**
     * Updates the information of an existing user, including their login, password, and role.
     *
     * @param userId the ID of the user to be updated
     * @param userLogin the new login for the user
     * @param userPassword the new password for the user
     * @param userRoleId the new role ID for the user
     */
    public void updateUser(int userId, String userLogin, String userPassword, int userRoleId);

    /**
     * Displays information for a specific user based on their user ID.
     *
     * @param userId the ID of the user whose information is to be displayed
     * @return the user with the specified ID
     */
    public User showUserInformation(int userId);

    /**
     * Deletes a specific user from the system based on their user ID.
     *
     * @param userId the ID of the user to be deleted
     */
    public void deleteUser(int userId);

    /**
     * Updates the authorization status of a specific user.
     * If the status is true, the user is authorized; if false, the user is not authorized.
     *
     * @param userId the ID of the user whose authorization status is to be updated
     * @param status the new authorization status for the user
     */
    public void updateAuthorizationStatus(int userId, boolean status);

    /**
     * Updates the block status of a specific user.
     * If the status is true, the user is blocked; if false, the user is unblocked.
     *
     * @param userId the ID of the user whose block status is to be updated
     * @param status the new block status for the user
     */
    public void updateBlockStatus(int userId, boolean status);

    /**
     * Checks if a specific user is blocked.
     *
     * @param userId the ID of the user to check
     * @return true if the user is blocked, false otherwise
     */
    public boolean isUserBlocked(int userId);

    public int getUserRole(int userId);
}
