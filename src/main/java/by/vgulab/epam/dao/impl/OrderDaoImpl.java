package by.vgulab.epam.dao.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.OrderDao;
import by.vgulab.epam.domain.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public List<Order> readAll() throws DaoException {
        final String readAll = "SELECT * FROM order_m";

        Statement statement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(readAll);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setTourId(resultSet.getLong("tour_id"));
                order.setPrice(resultSet.getLong("price"));

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean isOrderInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    @Override
    public Order read(Long id) throws DaoException {
        final String readOrder = "SELECT * FROM order WHERE id = ?";


        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            statement = getConnection().prepareStatement(readOrder);
            statement.setLong(1, id);
            Order order = null;

            if (resultSet.next()) {
                order = new Order();
                order.setId(id);
                order.setUserId(resultSet.getLong("user_id"));
                order.setUserId(resultSet.getLong("tour_id"));
                order.setPrice(resultSet.getLong("price"));
            }
            return order;

        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }


    }

    @Override
    public Long create(Order order) throws DaoException {
        final String create = "INSERT INTO order (user_id, tour_is, price) VALUES (?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getTourId());
            statement.setLong(3, order.getPrice());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;

        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        final String updateQuery = "UPDATE order SET user_id = ?, tour_id = ?, price = ? WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = getConnection().prepareStatement(updateQuery);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getTourId());
            statement.setLong(3, order.getPrice());
            statement.setLong(4, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM order WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(delete);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {

            }
        }
    }

}



