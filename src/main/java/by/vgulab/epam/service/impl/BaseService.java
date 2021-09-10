package by.vgulab.epam.service.impl;

import by.vgulab.epam.service.Transaction;

abstract public class BaseService {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
