package com.TrustAPI.TAPI.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientRegistrationRequest(
        @NotBlank
        @Size(max = 100)
        String name
) {}