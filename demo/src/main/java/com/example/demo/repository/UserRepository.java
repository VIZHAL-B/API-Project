package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Fetch user by email
    Optional<User> findByEmail(String email);

    // Fetch users by preferred language with sorting
    List<User> findByPreferredLanguage(String preferredLanguage, Sort sort);

    // ✅ Pagination support (Remove @NotNull)
    Page<User> findAll(Pageable pageable);
}
