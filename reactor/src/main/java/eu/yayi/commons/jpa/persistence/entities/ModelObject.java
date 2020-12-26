package eu.yayi.commons.jpa.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class ModelObject<S extends Serializable> implements Model<S> {

    @Id
    private S id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATING_DATE")
    private Date updatingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETING_DATE")
    private Date deletingDate;

    public void setDeletingDate(Date deletingDate) {
        this.deletingDate = deletingDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDeletingDate() {
        return deletingDate;
    }

    public Date getUpdatingDate() {
        return updatingDate;
    }

    public void setUpdatingDate(Date updatingDate) {
        this.updatingDate = updatingDate;
    }

}
