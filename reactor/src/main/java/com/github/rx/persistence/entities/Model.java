package com.github.rx.persistence.entities;

import java.io.Serializable;

public interface Model<S extends Serializable> {
    S getId();
}

