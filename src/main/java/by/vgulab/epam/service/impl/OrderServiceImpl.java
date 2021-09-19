package by.vgulab.epam.service.impl;

import by.vgulab.epam.dao.DaoException;
import by.vgulab.epam.dao.OrderDao;
import by.vgulab.epam.domain.Order;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() throws ServiceException {


        try {
            log.info("transition to findAll Order");
            return orderDao.readAll();

        } catch (DaoException e) {
            log.error("transition to" + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> findOrder(Long id) throws ServiceException {
        try {
            log.info("transition to findById Order");

            return orderDao.readOrder(id);

        } catch (DaoException e) {
            log.error("Didn't find one" + e.getMessage());
            throw new ServiceException(e);
        }

    }

    @Override
    public void createOrder(Order order) {
        Long paymentOrderDiscont = order.getPayment();
        List<Order> orderList = new ArrayList<>();
        try {
            orderList.addAll(orderDao.readOrder(order.getUserId()));


//            orderList = orderDao.readAll();
//            orderList.addAll(orderDao.readAll());

//            orderList.addAll(orderDao.readAll());
//            orderList.addAll(orderDao.readOrder(order.getUserId()));

//            orderDao.readOrder(order.getUserId());


            int discontControl = 0;
//            for (Order orders : orderList) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getUserId().equals(order.getUserId())) {
                    discontControl++;
                }
            }

            switch (discontControl) {
                case 0:
                    paymentOrderDiscont = paymentOrderDiscont;
                    break;
                case 1:
                    paymentOrderDiscont -= 30L;
                    break;
                case 2:
                    paymentOrderDiscont -= 40L;
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    paymentOrderDiscont -= 50L;
                    break;
                default:
                    paymentOrderDiscont -= 70L;
                    break;
            }

            order.setPayment(paymentOrderDiscont);


            orderDao.create(order);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}

