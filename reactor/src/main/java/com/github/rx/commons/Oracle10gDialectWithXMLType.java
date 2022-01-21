package com.github.rx.commons;

import oracle.xdb.XMLType;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.service.ServiceRegistry;

import javax.xml.bind.JAXBException;

public class Oracle10gDialectWithXMLType extends Oracle10gDialect {

    public Oracle10gDialectWithXMLType() {
        super();
        registerHibernateType(XMLType._SQL_TYPECODE, "XMLTYPE");
        registerColumnType(XMLType._SQL_TYPECODE, "XMLTYPE");
    }
    @Override
    public void contributeTypes(final TypeContributions typeContributions,
                                final ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        //registerTypes(typeContributions);
    }

    /*
    private void registerTypes(final TypeContributions typeContributions) {
        try {
            typeContributions.contributeType(new OrderUserType(), new String[]{"Order"});
        } catch (JAXBException e) {
            throw new RuntimeException("Error registering Hibernate custom JAXB types", e);
        }
    }
     */
}
