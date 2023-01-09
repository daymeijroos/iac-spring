package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.enums.ProductFilter;
import com.daymeijroos.iacspring.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Query("delete from Product p where p.id = ?1")
    void deleteById(@NonNull String id);

    Optional<Product> findProductByName(String name);

    List<Product> findProductsByFilter(ProductFilter filter);
}
