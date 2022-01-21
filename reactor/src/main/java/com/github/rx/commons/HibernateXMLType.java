package com.github.rx.commons;

import com.github.rx.Reactor;
import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HibernateXMLType implements UserType, Serializable {
    static Logger logger = LoggerFactory.getLogger(Reactor.class);


    private static final long serialVersionUID = 2308230823023l;
    private static final Class returnedClass = String.class;
    private static final int[] SQL_TYPES = new int[] { oracle.xdb.XMLType._SQL_TYPECODE };

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return returnedClass;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null && y == null) return true;
        else if (x == null && y != null ) return false;
        else return x.equals(y);
    }


    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        logger.info("!!!!!!!!!!!!!!!!   ResultSet    !!!!!!!!!!!!!!!!");
        XMLType xmlType = null;
        Document doc = null;
        String returnValue = null;
        try {
            logger.debug("rs type: " + rs.getClass().getName() + ", value: " + rs.getObject(names[0]));
            xmlType = (XMLType) rs.getObject(names[0]);

            if (xmlType != null) {
                returnValue = xmlType.getStringVal();
            }
        } finally {
            if (null != xmlType) {
                xmlType.close();
            }
        }
        return returnValue;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        logger.info("!!!!!!!!!!!!!!!!   PreparedStatement    !!!!!!!!!!!!!!!!");
        if (logger.isTraceEnabled()) {
            logger.trace("  nullSafeSet: " + value + ", ps: " + st + ", index: " + index);
        }
        try {
            XMLType xmlType = null;
            if (value != null) {
                //xmlType = XMLType.createXML(getOracleConnection(st.getConnection()), (String)value);
                xmlType = XMLType.createXML((OPAQUE) value);
            }
            st.setObject(index, xmlType);
        } catch (Exception e) {
            throw new SQLException("Could not convert String to XML for storage: " + (String)value);
        }
    }


    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) {
            return null;
        } else {
            return value;
        }
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        try {
            return (Serializable)value;
        } catch (Exception e) {
            throw new HibernateException("Could not disassemble Document to Serializable", e);
        }
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {

        try {
            return (String)cached;
        } catch (Exception e) {
            throw new HibernateException("Could not assemble String to Document", e);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }


}


