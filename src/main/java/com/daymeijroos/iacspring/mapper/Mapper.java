package com.daymeijroos.iacspring.mapper;

import jakarta.annotation.Nonnull;

public interface Mapper<E, D> {
    E fromDTOToEntity(@Nonnull D d);
    D fromEntityToDTO(@Nonnull E e);
}