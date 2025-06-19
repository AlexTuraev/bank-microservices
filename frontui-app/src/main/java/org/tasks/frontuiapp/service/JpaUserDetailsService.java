package org.tasks.frontuiapp.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JpaUserDetailsService implements UserDetailsService {

    private final OAuth2AuthorizedClientManager manager;
    private final RestTemplate restTemplate;

    public JpaUserDetailsService(OAuth2AuthorizedClientManager manager, RestTemplate restTemplate) {
        this.manager = manager;
        this.restTemplate = restTemplate;
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

            // -------------------------------------------------------------------------------------
            RequestEntity<Void> requestEntity = RequestEntity
                    .get("http://accounts-app/account")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .build();
            var s = restTemplate.exchange(requestEntity, String.class);
            // -------------------------------------------------------------------------------------

            return new User(
                    "admin", "password", List.of()
            );
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Not found or service is down");
        }


    }

}