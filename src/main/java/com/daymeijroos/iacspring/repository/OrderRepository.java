package com.daymeijroos.iacspring.repository;

import com.daymeijroos.iacspring.Order.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, String> {
    @Modifying
    @Query("delete from Order p where p.id = ?1")
    void deleteById(@NonNull String id);

    List<Order> findByUserId(String userId);
}