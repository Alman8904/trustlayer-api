package com.TrustAPI.TAPI.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientRegistrationRequest(
        @NotBlank(message = "Client name is required")
        @Size(max = 100, message = "Client name must not exceed 100 characters")
        String name
) {}