package com.istore.mysqldbservice.factory;

public class Context {
    private AbstractRepositoryFactory abstractRepositoryFactory;

    public Context(AbstractRepositoryFactory abstractRepositoryFactory) {
        this.abstractRepositoryFactory = abstractRepositoryFactory;
    }

    public AbstractRepositoryFactory getFactory(){
        return abstractRepositoryFactory;
    }
}
