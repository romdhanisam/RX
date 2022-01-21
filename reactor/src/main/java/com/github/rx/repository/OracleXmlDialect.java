package com.github.rx.repository;

import oracle.xdb.XMLType;
import org.hibernate.dialect.Oracle10gDialect;

public class OracleXmlDialect extends Oracle10gDialect {

    public OracleXmlDialect() {
        registerColumnType(XMLType.XMLTYPE_PICKLE_AS_BINXML, "XMLTYPE");
    }
}

