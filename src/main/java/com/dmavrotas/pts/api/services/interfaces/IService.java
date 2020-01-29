package com.dmavrotas.pts.api.services.interfaces;

public interface IService<T>
{
    T getEntity(int id);

    T saveEntity(T entity);

    boolean deleteEntity(T entity);
}
