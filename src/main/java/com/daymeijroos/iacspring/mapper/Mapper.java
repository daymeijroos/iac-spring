package com.daymeijroos.iacspring.mapper;

public interface Mapper<E, D> {
    E fromDTOToEntity(D d);
    D fromEntityToDTO(E e);
    E fromIdToEntity(String id);
}