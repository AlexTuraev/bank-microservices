package org.tasks.frontuiapp.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tasks.frontuiapp.dto.UserDto;
import org.tasks.frontuiapp.service.FrontuiService;

import java.util.List;

@Service
public class FrontuiServiceImpl implements FrontuiService {

    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager manager;

    public FrontuiServiceImpl(RestTemplate restTemplate, OAuth2AuthorizedClientManager manager) {
        this.restTemplate = restTemplate;
        this.manager = manager;
    }

    @Override
    public Boolean createAccount(UserDto userDto) {
        try {
            OAuth2AuthorizedClient client = manager.authorize(OAuth2AuthorizeRequest
                    .withClientRegistrationId("front-ui")
                    .principal("system")
                    .build()
            );

            String accessToken = client.getAccessToken().getTokenValue();

            // -------------------------------------------------------------------------------------
            RequestEntity<UserDto> requestEntity = RequestEntity
                    .post("http://accounts-app/account")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .body(userDto);

            var s = restTemplate.exchange(requestEntity, String.class);
            // -------------------------------------------------------------------------------------

            return Boolean.TRUE;
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Not found or service is down");
        }
    }

}
