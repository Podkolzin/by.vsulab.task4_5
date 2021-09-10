package by.vgulab.epam.dao.Impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.TourDao;
import by.vgulab.epam.domain.Foot;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.Type;
import by.vgulab.epam.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl extends BaseDaoImpl implements TourDao {
    @Override
    public List<Tour> readAll() throws DaoException {

        Statement statement = null;
        ResultSet resultSet = null;

        final String findAllQuery = "SELECT * FROM tour ORDER BY id";

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(findAllQuery);
            List<Tour> tours = new ArrayList<>();

            while (resultSet.next()) {
                Tour tour = new Tour();

                tour.setType(Type.values()[resultSet.getInt("type")]);
                tour.setCountry(resultSet.getString("country"));
                tour.setTown(resultSet.getString("town"));
                tour.setDate(resultSet.getTime("date"));
                tour.setFoot(Foot.values()[resultSet.getInt("foot")]);
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
    public User readByCountry(String county) throws DaoException {
        return null;
    }

    @Override
    public boolean isUserInitiatesTransfers(Long id) throws DaoException {
        return false;
    }

    @Override
    public Tour read(Long id) throws DaoException {

        final String read = "SELECT  * FROM tour WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = getConnection().prepareStatement(read);
            preparedStatement.setString(1, "id");
            Tour tour = null;
            if (resultSet.next()) {
                tour = new Tour();

                tour.setId(id);
                tour.setType(Type.values()[resultSet.getInt("type")]);
                tour.setCountry(resultSet.getString("country"));
                tour.setTown(resultSet.getString("town"));
                tour.setDate(resultSet.getDate("date"));
                tour.setDay(resultSet.getInt("day"));
                tour.setFoot(Foot.values()[resultSet.getInt("foot")]);
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
        return null;
    }

    @Override
    public void update(Tour tour) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
