package eu.yayi.commons.jpa.persistence.entities;

import java.io.Serializable;
import java.util.Date;

public interface Model<S extends Serializable> {

    public void setDeletingDate(Date deletingDate);
    public void setUpdatingDate(Date updatingDate);
    public void setCreationDate(Date creationDate);
    public Date getCreationDate() ;
    public Date getUpdatingDate() ;
    public Date getDeletingDate();
}