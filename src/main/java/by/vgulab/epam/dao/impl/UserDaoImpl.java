package by.vgulab.epam.dao.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.UserDao;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.pool.ConnectionPool;
import by.vgulab.epam.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public List<User> readAll() throws DaoException {
        final String findAllQuery = "SELECT * FROM user ORDER BY id";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllQuery)) {
            List<User> usersAll = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                usersAll.add(user);
            }
            return usersAll;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean isUserInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        return null;
    }

    @Override
    public User read(Long id) throws DaoException {
        final String read = "SELECT  * FROM user WHERE id = ?";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(read)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    user.setId(id);
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(Role.values()[resultSet.getInt("role")]);
                }
                return user;
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        final String create = "INSERT INTO user (login, password, name, surname, email, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys();
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public void update(User user) throws DaoException {
        final String updateQuery = "UPDATE user SET login = ?, password = ?, name = ?, surname = ?, email = ?, role = ? WHERE id = ?";


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)
        ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getRole().ordinal());
            statement.setLong(7, user.getId());
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM user WHERE id = ?";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}
