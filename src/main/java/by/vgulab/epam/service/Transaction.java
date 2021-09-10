package by.vgulab.epam.service;

import by.vgulab.epam.service.exception.TransactionException;

public interface Transaction {

    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

}
