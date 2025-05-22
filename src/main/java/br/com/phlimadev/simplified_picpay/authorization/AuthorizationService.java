package br.com.phlimadev.simplified_picpay.authorization;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class AuthorizationService {
    private static final String AUTH_URL = "https://util.devi.tools/api/v2/authorize";

    public boolean isAuthorized() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            AuthorizationResponse response = restTemplate.getForObject(AUTH_URL, AuthorizationResponse.class);
            return response != null &&
                    "success".equalsIgnoreCase(response.status()) &&
                    response.data() != null &&
                    Boolean.TRUE.equals(response.data().authorization());
        } catch (RestClientException e) {
            return false;
        }
    }

    public record AuthorizationResponse(String status, Data data) {
        public record Data(Boolean authorization) {}
    }
}
