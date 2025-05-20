package ru.rsreu.straxov.datalayer.oracledb;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import ru.rsreu.straxov.datalayer.data.boundary.UserResultSetProcessor;
import ru.rsreu.straxov.datalayer.data.daointerfaces.UserDAO;
import ru.rsreu.straxov.datalayer.data.entities.Lot;
import ru.rsreu.straxov.datalayer.data.entities.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OracleUserDAO implements UserDAO {
    private Connection connection;
    protected static Resourcer resourcer = ProjectResourcer.getInstance();

    public OracleUserDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> getAllUsersAndRolesModer() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> allUsers = new ArrayList<User>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getAllUsersModer"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = UserResultSetProcessor.usersAndRoles(resultSet);
                allUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    @Override
    public List<User> getAllUsersAndRolesAdmin() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> allUsers = new ArrayList<User>();

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getAllUsersAdmin"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = UserResultSetProcessor.usersAndRoles(resultSet);
                allUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    @Override
    public User findUser(String login, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getUserToEnter"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return UserResultSetProcessor.findUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void createUser(User user) {
        PreparedStatement preparedStatement = null;;

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.createUser"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setString(1, user.getUserLogin());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setInt(3, user.getUserRoleId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUser(int userId, String userLogin, String userPassword, int userRoleId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.updateUserInformation"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setString(1,userLogin);
            preparedStatement.setString(2,userPassword);
            preparedStatement.setInt(3,userRoleId);
            preparedStatement.setInt(4,userId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User showUserInformation(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.showUserInfo"), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            preparedStatement.setInt(1,userId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {  // Добавляем проверку наличия результата
                return UserResultSetProcessor.userInformation(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteUser(int userId) {
        PreparedStatement deleteFromAllUsers = null;
        try {
            // Проверяем и удаляем записи из вспомогательных таблиц
            checkAndDelete(userId,
                    resourcer.getString("message.query.checkRequests"),
                    resourcer.getString("message.query.deleteFromRequests")
            );

            checkAndDelete(userId,
                    resourcer.getString("message.query.checkBids"),
                    resourcer.getString("message.query.deleteFromBids")
            );

            checkAndDelete(userId,
                    resourcer.getString("message.query.checkLots"),
                    resourcer.getString("message.query.deleteFromLots")
            );

            checkAndDelete(userId,
                    resourcer.getString("message.query.checkPurchases"),
                    resourcer.getString("message.query.deleteFromPurchases")
            );

            // Удаляем пользователя из основной таблицы
            deleteFromAllUsers = connection.prepareStatement(
                    resourcer.getString("message.query.deleteFromAllUsers")
            );
            deleteFromAllUsers.setInt(1, userId);
            deleteFromAllUsers.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (deleteFromAllUsers != null)
                    deleteFromAllUsers.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateAuthorizationStatus(int userId, boolean status) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.updateAuthorizationStatus"),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            preparedStatement.setInt(1, status ? 1 : 0);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateBlockStatus(int userId, boolean status) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.updateBlockStatus"),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            preparedStatement.setInt(1, status ? 0 : 1);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isUserBlocked(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.checkBlockStatus"),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return UserResultSetProcessor.isBlocked(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public int getUserRole(int userId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    resourcer.getString("message.query.getUserRole")
            );

            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("userId" + userId);
                System.out.println("getUserRole" + UserResultSetProcessor.getRoleId(resultSet));
                return UserResultSetProcessor.getRoleId(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    private void checkAndDelete(int userId, String checkQuery, String deleteQuery) throws SQLException {
        PreparedStatement checkStatement = null;
        PreparedStatement deleteStatement = null;
        try {
            // Проверяем наличие записей
            checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, userId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) { // Если записи существуют, удаляем
                deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.executeUpdate();
            }
        } finally {
            // Освобождаем ресурсы
            if (checkStatement != null) checkStatement.close();
            if (deleteStatement != null) deleteStatement.close();
        }
    }

}
