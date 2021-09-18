package by.vgulab.epam.dao;

import by.vgulab.epam.domain.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<Order> readAll() throws DaoException;

    List<Order> readOrder(Long id) throws DaoException;

    boolean isOrderInitiatesTransfers(Long id) throws DaoException;



}
