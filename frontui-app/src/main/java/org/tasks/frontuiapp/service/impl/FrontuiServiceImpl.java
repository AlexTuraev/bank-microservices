package org.tasks.frontuiapp.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tasks.frontuiapp.dto.ChangePswDto;
import org.tasks.frontuiapp.dto.UserDto;
import org.tasks.frontuiapp.service.FrontuiService;

import java.util.List;

@Service
public class FrontuiServiceImpl implements FrontuiService {

    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager manager;
    private final PasswordEncoder passwordEncoder;

    public FrontuiServiceImpl(RestTemplate restTemplate, OAuth2AuthorizedClientManager manager, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean createAccount(UserDto userDto) {
        try {
            String accessToken = getOauth2Token();
            // -------------------------------------------------------------------------------------

            RequestEntity<UserDto> requestEntity = RequestEntity
                    .post("http://accounts-app/account")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .body(new UserDto(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()), userDto.getName(), userDto.getBirthdate()));

            var s = restTemplate.exchange(requestEntity, String.class);
            // -------------------------------------------------------------------------------------

            return Boolean.TRUE;
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Not found or service is down");
        }
    }

    @Override
    public void changePassword(String login, String password) {
        String accessToken = getOauth2Token();

        RequestEntity<ChangePswDto> requestEntity = RequestEntity
                .post("http://accounts-app/editpsw")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(ChangePswDto.builder()
                        .login(login)
                        .passwordHash(passwordEncoder.encode(password))
                        .build());

        restTemplate.exchange(requestEntity, String.class);
    }

    private String getOauth2Token() {
        OAuth2AuthorizedClient client = manager.authorize(OAuth2AuthorizeRequest
                .withClientRegistrationId("front-ui")
                .principal("system")
                .build()
        );

        return client.getAccessToken().getTokenValue();
    }

}
