package com.daymeijroos.iacspring.Category;

import com.daymeijroos.iacspring.Category.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Modifying
    @Query("delete from Category p where p.id = ?1")
    void deleteById(@NonNull String id);
}