package by.vgulab.epam.dao.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.domain.Food;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl extends BaseDaoImpl implements TourDao {
    @Override
    public List<Tour> readAll() throws DaoException {
        final String findAllQuery = "SELECT * FROM tour ORDER BY id";
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(findAllQuery);
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = getConnection().prepareStatement(read);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

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


        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Tour tour) throws DaoException {

        final String create = "INSERT INTO tour (type, country, town, date, day, food, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, tour.getType().ordinal());
            statement.setString(2, tour.getCountry());
            statement.setString(3, tour.getTown());
            statement.setDate(4, tour.getDate());
            statement.setInt(5, tour.getDay());
            statement.setInt(6, tour.getFood().ordinal());
            statement.setInt(7, tour.getPrice());
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
    public void update(Tour tour) throws DaoException {
        final String updateQuery = "UPDATE tour SET type = ?, country = ?, town = ?, date = ?, day = ?, food = ?, price = ? WHERE id = ?";

        PreparedStatement statement = null;

        try {
            statement = getConnection().prepareStatement(updateQuery);
            statement.setInt(1, tour.getType().ordinal());
            statement.setString(2, tour.getCountry());
            statement.setString(3, tour.getTown());
            statement.setDate(4, (Date) tour.getDate());
            statement.setInt(5, tour.getDay());
            statement.setInt(6, tour.getFood().ordinal());
            statement.setInt(7, tour.getPrice());
            statement.setLong(8, tour.getId());
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

    @Override
    public void delete(Long id) throws DaoException {
        final String delete = "DELETE FROM tour WHERE id = ?";

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
