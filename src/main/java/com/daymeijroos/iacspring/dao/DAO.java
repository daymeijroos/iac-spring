package com.daymeijroos.iacspring.dao;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T getById(String id) throws ResourceNotFoundException;
    T saveToDatabase(T t);
    T update(String id, T t) throws ResourceNotFoundException;
    void delete(String id) throws ResourceNotFoundException;
}