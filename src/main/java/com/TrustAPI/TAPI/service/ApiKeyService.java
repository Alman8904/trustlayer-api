package com.TrustAPI.TAPI.service;

import com.TrustAPI.TAPI.entity.ApiKey;
import com.TrustAPI.TAPI.entity.Client;
import com.TrustAPI.TAPI.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateAndSaveApiKey(Client client) {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        String rawKey = "tk_live_" +
                Base64.getUrlEncoder()
                        .withoutPadding()
                        .encodeToString(bytes);

        try {
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(rawKey.getBytes(StandardCharsets.UTF_8));

            String keyHash = HexFormat.of().formatHex(hash);

            apiKeyRepository.save(new ApiKey(client, keyHash));

            return rawKey;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 unavailable", e);
        }
    }
}