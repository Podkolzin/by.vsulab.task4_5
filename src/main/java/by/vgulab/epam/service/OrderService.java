package by.vgulab.epam.service;

import by.vgulab.epam.domain.Order;

import by.vgulab.epam.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    List<Order> findAll() throws ServiceException;

    Order findById(Long id) throws ServiceException;

    List<Order> findOrder(Long id) throws ServiceException;

    void createOrder(Order order) throws ServiceException;


}
