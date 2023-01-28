package com.daymeijroos.iacspring.Admin;

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
    Optional<Admin> findByUserId(String userId);
}