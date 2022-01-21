package com.github.rx.repository;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;
import oracle.xdb.XMLType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OracleXMLTypeMapper extends SqlXmlTypeMapper{
    private final int[] TYPES = new int[] { XMLType.XMLTYPE_PICKLE_AS_BINXML };

    public static final OracleXMLTypeMapper INSTANCE = new OracleXMLTypeMapper();

    public OracleXMLTypeMapper() {
        super();
    }

    @Override
    public int[] sqlTypes() {
        return TYPES.clone();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor ssci, Object o) throws HibernateException, SQLException {
        OracleResultSet ors = rs.unwrap(OracleResultSet.class);

        XMLType xmlType =(XMLType) ors.getObject(names[0]); // oracle xml type are stored as CLOB
        if(xmlType != null){
            String xml = xmlType.getString().trim();
            return xml.getBytes(StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor ssci) throws HibernateException, SQLException {
        XMLType xmlType = null;
        OracleConnection oracleConnection = st.getConnection().unwrap(OracleConnection.class);
        if (value instanceof byte[]) {
            String xml = new String((byte[]) value, StandardCharsets.UTF_8);
            xmlType = XMLType.createXML(oracleConnection, xml.trim(),"oracle.xml.parser.XMLDocument.THIN");
        }
        st.setObject(index, xmlType);
    }
}
