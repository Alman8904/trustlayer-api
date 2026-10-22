package com.TrustAPI.TAPI.dto.client;

public record ClientRegistrationResponse(
        Long id,
        String name,
        String email,
        String apiKey
) {}