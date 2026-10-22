package com.TrustAPI.TAPI.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record ClientRegistrationRequest(
        @NotBlank(message = "Client name is required")
        @Size(max = 100, message = "Client name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email format")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email
) {}