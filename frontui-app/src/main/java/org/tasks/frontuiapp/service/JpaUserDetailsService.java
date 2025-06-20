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
import org.tasks.frontuiapp.dto.UserDto;

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

            RequestEntity<String> requestEntity = RequestEntity
                    .post("http://accounts-app/auth")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .body(username);

            UserDto userDto = restTemplate.exchange(requestEntity, UserDto.class).getBody();

            return new User(
                    userDto.getLogin(), userDto.getPassword(), List.of()
            );
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Not found or service is down");
        }


    }

}