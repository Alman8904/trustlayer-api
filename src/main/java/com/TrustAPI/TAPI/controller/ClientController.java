package com.TrustAPI.TAPI.controller;

import com.TrustAPI.TAPI.dto.client.ClientRegistrationRequest;
import com.TrustAPI.TAPI.dto.client.ClientRegistrationResponse;
import com.TrustAPI.TAPI.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientRegistrationResponse registerClient(
            @Valid @RequestBody ClientRegistrationRequest request) {

        return clientService.registerClient(request);
    }
}