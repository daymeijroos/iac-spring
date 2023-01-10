package com.daymeijroos.iacspring.Order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LineItemRepository extends JpaRepository<LineItem, String> {
    @Modifying
    @Query("delete from Order p where p.id = ?1")
    void deleteById(@NonNull String id);
}
