package com.github.rx.persistence.entities;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelObject<S extends Serializable> {

    public abstract S getId();


}