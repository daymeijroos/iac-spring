package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Query("delete from Product p where p.id = ?1")
    void deleteById(@NonNull String id);
}
