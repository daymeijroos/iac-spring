package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.Product.ProductFilter;
import com.daymeijroos.iacspring.Product.Product;
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
    Optional<Product> findProductByName(String name);

    List<Product> findProductsByFilter(ProductFilter filter);
}
