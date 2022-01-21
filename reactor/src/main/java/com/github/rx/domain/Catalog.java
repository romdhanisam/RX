package com.github.rx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
@NoArgsConstructor
@Entity
/*
@TypeDefs({
        @TypeDef(name = "XMLTYPE", typeClass = HibernateXMLType.class)//,CatalogXmlType
})
*/
@Table(name = "t_catalog")
public class Catalog implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "UUID")
    /*
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    */
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fileName")
    private String fileName;

    @Lob
    //eu.yayi.commons.OracleDialectExtension
    //@Column(name = "data", columnDefinition="BLOB")
    @Type(type = "xmltype")
    //@Type(type = "eu.yayi.commons.CatalogXmlType")
    //@Type(type = "XMLTYPE")
    //CatalogXmlType
    @Column(name = "XML_CONTENT")
    //@Basic
    //@XmlElement(name = "XML_CONTENT")
    private byte[] file; //

    @Override
    public String toString() {
        return "Catalog{" +
                "fileName=" + fileName +
                "file=" + new String(file, StandardCharsets.UTF_8) +
                '}';
    }
}
