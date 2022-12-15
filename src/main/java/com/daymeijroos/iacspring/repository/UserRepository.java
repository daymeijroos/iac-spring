package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.model.User;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Query("delete from User p where p.id = ?1")
    void deleteById(@NonNull String id);

    Optional<User> findByEmail(@Email String email);
}