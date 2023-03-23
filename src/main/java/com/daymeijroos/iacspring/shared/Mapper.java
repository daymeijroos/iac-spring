package com.daymeijroos.iacspring.shared;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import jakarta.annotation.Nonnull;

public interface Mapper<E, D> {
    E fromDTOToEntity(@Nonnull D d) throws ResourceNotFoundException;
    D fromEntityToDTO(@Nonnull E e);
}