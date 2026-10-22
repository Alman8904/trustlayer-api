package com.TrustAPI.TAPI.repository;

import com.TrustAPI.TAPI.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByKeyHashAndRevokedAtIsNull(String keyHash);
}
