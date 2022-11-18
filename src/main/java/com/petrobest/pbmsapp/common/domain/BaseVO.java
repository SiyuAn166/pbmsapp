package com.petrobest.pbmsapp.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVO<T> implements Serializable {

    private Long currPage;
    private Long pageSize;

    private T entity;
}
