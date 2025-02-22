package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Custom JPQL query to fetch users within a given ID range
    @Query("SELECT u FROM User u WHERE u.id BETWEEN :startId AND :endId")
    List<User> findUsersBetweenIds(@Param("startId") Long startId, @Param("endId") Long endId);

    // ✅ Find user by email
    Optional<User> findByEmail(String email);

    // ✅ Fetch users by preferred language with sorting
    List<User> findByPreferredLanguage(String language, Sort sort);
}
