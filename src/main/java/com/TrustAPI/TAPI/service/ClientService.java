package com.TrustAPI.TAPI.service;

import com.TrustAPI.TAPI.entity.Client;
import com.TrustAPI.TAPI.repository.ApiKeyRepository;
import com.TrustAPI.TAPI.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ApiKeyRepository apiKeyRepository;

    //create a new client
    public Client ClientRegistration(String name) {
        Client client = new Client(name);
        return clientRepository.save(client);
    }

    //
}
