package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.model.Admin;
import com.daymeijroos.iacspring.model.ShippingDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Modifying
    @Query("delete from ShippingDetails p where p.id = ?1")
    void deleteById(@NonNull String id);

    Optional<Admin> findByUserId(String userId);
}