package com.tshortly.url.repository;

import com.tshortly.url.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortUrlCode(String shortUrlCode);
    boolean existsByShortUrlCode(String shortUrlCode);
}