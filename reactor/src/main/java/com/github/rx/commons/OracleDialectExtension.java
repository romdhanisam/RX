package com.github.rx.commons;


import org.hibernate.dialect.Oracle10gDialect;

import javax.xml.bind.annotation.XmlType;
import java.sql.Types;

public class OracleDialectExtension extends Oracle10gDialect {
    public OracleDialectExtension() {
        super();
        registerColumnType(Types.SQLXML, "CatalogXmlType");
        registerColumnType(Types.BLOB, "CatalogXmlType");
        registerColumnType(Types.CLOB, "xmltype");
    }

    @Override
    public boolean useInputStreamToInsertBlob() {
        return false;
    }
}
