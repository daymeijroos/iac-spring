package com.daymeijroos.iacspring.ShippingDetails;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, String> {
    Optional<ShippingDetails> findFirstByUserId(String userId);
}