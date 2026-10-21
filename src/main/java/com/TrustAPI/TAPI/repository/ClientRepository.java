package com.TrustAPI.TAPI.repository;

import com.TrustAPI.TAPI.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
