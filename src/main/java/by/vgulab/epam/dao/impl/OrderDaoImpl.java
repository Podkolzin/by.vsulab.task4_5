package by.vgulab.epam.dao.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.OrderDao;
import by.vgulab.epam.domain.*;
import by.vgulab.epam.pool.ConnectionPool;
import by.vgulab.epam.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public List<Order> readAll() throws DaoException {
        final String readAll = "SELECT * FROM user\n" +
                "                  INNER JOIN `order_m` ON user.id = `order_m`.user_id\n" +
                "                  INNER JOIN tour u on `order_m`.tour_id = u.id\n" +
                "ORDER BY user_id";

        List<Order> orders = new ArrayList<>();


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(readAll)) {

            while (resultSet.next()) {
                Order order = new Order();
                User user = new User();
                Tour tour = new Tour();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setTourId(resultSet.getLong("tour_id"));
                order.setPayment(resultSet.getLong("payment"));

                user.setId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role")]);

                tour.setId(resultSet.getLong("tour_id"));
                tour.setType(Type.values()[resultSet.getInt("type")]);
                tour.setCountry(resultSet.getString("country"));
                tour.setTown(resultSet.getString("town"));
                tour.setDate(resultSet.getDate("date"));
                tour.setDay(resultSet.getInt("day"));
                tour.setFood(Food.values()[resultSet.getInt("food")]);
                tour.setPrice(order.getPayment().intValue());

                order.setUser(user);
                order.setTour(tour);
                orders.add(order);

                System.out.println(order.toString());
            }

            return orders;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean isOrderInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    public List<Order> readOrder(Long id) throws DaoException {
        final String readOrder = "SELECT * FROM tour\n" +
                "                  INNER JOIN `order_m` ON tour.id = `order_m`.tour_id\n" +
                "                  INNER JOIN user u on `order_m`.user_id = u.id\n" +
                "where user_id = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(readOrder)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Order order = new Order();
                    User user = new User();
                    Tour tour = new Tour();
                    order.setUserId(resultSet.getLong("user_id"));
                    order.setTourId(resultSet.getLong("tour_id"));
                    order.setPayment(resultSet.getLong("payment"));
                    order.setId(resultSet.getLong("id"));


                    user.setId(resultSet.getLong("user_id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(Role.values()[resultSet.getInt("role")]);
                    order.setUser(user);

                    tour.setId(resultSet.getLong("tour_id"));
                    tour.setType(Type.values()[resultSet.getInt("type")]);
                    tour.setCountry(resultSet.getString("country"));
                    tour.setTown(resultSet.getString("town"));
                    tour.setDate(resultSet.getDate("date"));
                    tour.setDay(resultSet.getInt("day"));
                    tour.setFood(Food.values()[resultSet.getInt("food")]);
                    tour.setPrice(order.getPayment().intValue());
                    order.setTour(tour);
                    orders.add(order);
                }
                return orders;

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public Order read(Long id) throws DaoException {
        return null;
    }

    @Override
    public Long create(Order order) throws DaoException {
        final String create = "INSERT INTO `order_m` (user_id, tour_id, payment) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getTourId());
            statement.setLong(3, order.getPayment());
            statement.executeUpdate();
            Long id = null;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
                return id;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        final String updateQuery = "UPDATE `order_m` SET user_id = ?, tour_id = ?, payment = ? WHERE id = ?";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getTourId());
            statement.setLong(3, order.getPayment());
            statement.setLong(4, order.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }


    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM order_m WHERE id = ?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

}



