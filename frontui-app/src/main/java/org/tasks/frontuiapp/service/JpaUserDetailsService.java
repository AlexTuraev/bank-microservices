package org.tasks.frontuiapp.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final OAuth2AuthorizedClientManager manager;
    private final RestClient restClient;

    public JpaUserDetailsService(OAuth2AuthorizedClientManager manager, RestClient restClient) {
        this.manager = manager;
        this.restClient = restClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            OAuth2AuthorizedClient client = manager.authorize(OAuth2AuthorizeRequest
                    .withClientRegistrationId("front-ui")
                    .principal("system")
                    .build()
            );

            String accessToken = client.getAccessToken().getTokenValue();

            ResponseEntity<String> responseEntity = restClient.get()
//                    .uri("http://accounts-app/account")
                    .uri("http://localhost:8083/account")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // Подставляем токен доступа в заголовок Authorization
                    .retrieve()
                    .toEntity(String.class);

            String accountAnswer = responseEntity.getBody();


            return new User(
                    "admin", "password", List.of()
            );
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Not found or service is down");
        }


    }

}