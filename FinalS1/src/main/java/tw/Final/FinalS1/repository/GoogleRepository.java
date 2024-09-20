package tw.Final.FinalS1.repository;

import java.util.Optional;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleRepository {

    ClientRegistrationRepository getClientRegistrationRepository();

    Optional<ClientRegistration> findByRegistrationId(String registrationId);
}