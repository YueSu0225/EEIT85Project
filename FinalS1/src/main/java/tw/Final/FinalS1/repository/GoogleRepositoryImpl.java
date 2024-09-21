package tw.Final.FinalS1.repository;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GoogleRepositoryImpl implements GoogleRepository {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public GoogleRepositoryImpl(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public ClientRegistrationRepository getClientRegistrationRepository() {
        return clientRegistrationRepository;
    }

    @Override
    public Optional<ClientRegistration> findByRegistrationId(String registrationId) {
        return Optional.ofNullable(clientRegistrationRepository.findByRegistrationId(registrationId));
    }
}
