package com.antonio.encurtador.repository;

import com.antonio.encurtador.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByUrlCurta(String urlCurta);

    void deleteByCreatedAtBefore(LocalDateTime createdAt);
}
