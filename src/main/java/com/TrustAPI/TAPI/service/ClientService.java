package com.TrustAPI.TAPI.service;

import com.TrustAPI.TAPI.dto.client.ClientRegistrationRequest;
import com.TrustAPI.TAPI.dto.client.ClientRegistrationResponse;
import com.TrustAPI.TAPI.entity.Client;
import com.TrustAPI.TAPI.exception.DuplicateEmailException;
import com.TrustAPI.TAPI.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ApiKeyService apiKeyService;

    //create a new client
    @Transactional
    public ClientRegistrationResponse registerClient(ClientRegistrationRequest request) {
        String email = request.email().trim().toLowerCase();

        if(clientRepository.existsByEmailIgnoreCase(email)){
            throw new DuplicateEmailException();
        }

        Client client = clientRepository.save(new Client(request.name(), email));

        String apiKey = apiKeyService.generateAndSaveApiKey(client);

        return new ClientRegistrationResponse(client.getId(), client.getName(), client.getEmail(),apiKey);
    }
}
