package com.dmavrotas.pts.api.services.interfaces;

/**
 *
 * @param <T>
 */
public interface IService<T>
{
    /**
     *
     * @param id
     * @return
     */
    T getEntity(int id);

    /**
     *
     * @param entity
     * @return
     */
    T saveEntity(T entity);

    /**
     *
     * @param entity
     * @return
     */
    boolean deleteEntity(T entity);
}
