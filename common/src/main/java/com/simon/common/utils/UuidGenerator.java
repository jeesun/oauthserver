package com.simon.common.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * uuid generator
 *
 * @author simon
 * @create 2018-08-06 11:31
 **/

public class UuidGenerator implements IdentifierGenerator {
    /*@Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return UuidUtils.getUUID();
    }*/

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return UuidUtils.getUUID();
    }
}
