package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.model.Order;
import com.daymeijroos.iacspring.model.ShippingDetails;
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
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, String> {
    @Modifying
    @Query("delete from ShippingDetails p where p.id = ?1")
    void deleteById(@NonNull String id);

    Optional<ShippingDetails> findByUserId(String userId);
}