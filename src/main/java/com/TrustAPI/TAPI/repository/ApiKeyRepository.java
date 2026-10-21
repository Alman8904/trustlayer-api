package com.TrustAPI.TAPI.repository;

import com.TrustAPI.TAPI.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
