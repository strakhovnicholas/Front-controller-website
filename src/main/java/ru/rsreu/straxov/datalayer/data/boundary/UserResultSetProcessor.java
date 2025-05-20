package ru.rsreu.straxov.datalayer.data.boundary;

import ru.rsreu.straxov.datalayer.data.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetProcessor {
    public static User usersAndRoles(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("USER_ID");
        String userLogin = resultSet.getString("LOGIN");
        String userPassword = resultSet.getString("USER_PASSWORD");
        Boolean userBlockedStatus = resultSet.getInt("BLOCKED_STATUS") == 1;
        Boolean userAuthorizedStatus = resultSet.getInt("AUTHORIZED_STATUS") == 1;
        int roleId = resultSet.getInt("ROLE_ID");
        return new User(userId, userLogin, userPassword, userBlockedStatus, userAuthorizedStatus, roleId);
    }

    public static User findUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("USER_ID");
        int roleId = resultSet.getInt("ROLE_ID");
        return new User(userId, roleId);
    }

    public static User userInformation(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("LOGIN"), resultSet.getString("USER_PASSWORD"), resultSet.getInt("ROLE_ID"));
    }

    public static boolean isBlocked(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("BLOCKED_STATUS") != 0;
    }

    public static int getRoleId(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("ROLE_ID");
    }
}
