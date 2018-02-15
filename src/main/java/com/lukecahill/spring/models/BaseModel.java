package com.lukecahill.spring.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class BaseModel {

    protected Timestamp lastEditedTime;
    protected Timestamp created;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    protected boolean isDeleted = false;

    public BaseModel() {}

    @PreRemove
    public void delete() {
        if (!this.isDeleted) {
            this.isDeleted = true;
            this.lastEditedTime = new Timestamp(new Date().getTime());
        }
    }

    @PreUpdate
    public void update() {
        this.lastEditedTime = new Timestamp(new Date().getTime());
    }

    @PrePersist
    public void initialInsert() {
        this.isDeleted = false;
        this.lastEditedTime = new Timestamp(new Date().getTime());
        this.created = new Timestamp(new Date().getTime());
    }

    public Timestamp getLastEdited() {
        return lastEditedTime;
    }

    public void setLastEdited(Timestamp lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
