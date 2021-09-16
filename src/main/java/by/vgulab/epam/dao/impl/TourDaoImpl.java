package by.vgulab.epam.dao.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.domain.Food;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.Type;
import by.vgulab.epam.pool.ConnectionPool;
import by.vgulab.epam.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl extends BaseDaoImpl implements TourDao {
    @Override
    public List<Tour> readAll() throws DaoException {
        final String findAllQuery = "SELECT * FROM tour ORDER BY id";



        try (Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllQuery))
        {
            List<Tour> tours = new ArrayList<>();
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getLong("id"));
                tour.setType(Type.values()[resultSet.getInt("type")]);
                tour.setCountry(resultSet.getString("country"));
                tour.setTown(resultSet.getString("town"));
                tour.setDate(resultSet.getDate("date"));
                tour.setDay(resultSet.getInt("day"));
                tour.setFood(Food.values()[resultSet.getInt("food")]);
                tour.setPrice(resultSet.getInt("price"));

                tours.add(tour);
            }
            return tours;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException();
        }
    }

    @Override
    public Tour readByCountry(String county) throws DaoException {
        return null;
    }

    @Override
    public boolean isTourInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    @Override
    public Tour read(Long id) throws DaoException {
        final String read = "SELECT  * FROM tour WHERE id = ?";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(read)){
             preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Tour tour = null;
                if (resultSet.next()) {
                    tour = new Tour();
                    tour.setId(id);
                    tour.setType(Type.values()[resultSet.getInt("type")]);
                    tour.setCountry(resultSet.getString("country"));
                    tour.setTown(resultSet.getString("town"));
                    tour.setDate(resultSet.getDate("date"));
                    tour.setDay(resultSet.getInt("day"));
                    tour.setFood(Food.values()[resultSet.getInt("food")]);
                    tour.setPrice(resultSet.getInt("price"));
                }
                return tour;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long create(Tour tour) throws DaoException {

        final String create = "INSERT INTO tour (type, country, town, date, day, food, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
        ) {
            statement.setInt(1, tour.getType().ordinal());
            statement.setString(2, tour.getCountry());
            statement.setString(3, tour.getTown());
            statement.setDate(4, tour.getDate());
            statement.setInt(5, tour.getDay());
            statement.setInt(6, tour.getFood().ordinal());
            statement.setInt(7, tour.getPrice());
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
    public void update(Tour tour) throws DaoException {
        final String updateQuery = "UPDATE tour SET type = ?, country = ?, town = ?, date = ?, day = ?, food = ?, price = ? WHERE id = ?";

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery))
        {
            statement.setInt(1, tour.getType().ordinal());
            statement.setString(2, tour.getCountry());
            statement.setString(3, tour.getTown());
            statement.setDate(4, (Date) tour.getDate());
            statement.setInt(5, tour.getDay());
            statement.setInt(6, tour.getFood().ordinal());
            statement.setInt(7, tour.getPrice());
            statement.setLong(8, tour.getId());
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM tour WHERE id = ?";

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(delete))        {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}
