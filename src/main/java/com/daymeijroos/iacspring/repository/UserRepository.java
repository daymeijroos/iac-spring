package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Query("delete from User p where p.id = ?1")
    void deleteById(@NonNull String id);
}