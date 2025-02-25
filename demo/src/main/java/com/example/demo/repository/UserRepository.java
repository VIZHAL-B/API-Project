package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Find users within a given ID range (JPQL)
    @Query("SELECT u FROM User u WHERE u.id BETWEEN :startId AND :endId")
    List<User> findUsersInRange(@Param("startId") Long startId, @Param("endId") Long endId);

    // ✅ Find users by name (JPQL)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findUsersByName(@Param("name") String name);
}
