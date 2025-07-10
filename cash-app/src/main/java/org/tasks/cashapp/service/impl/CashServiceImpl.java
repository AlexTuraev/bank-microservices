package org.tasks.cashapp.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tasks.commons.dto.CashDto;
import org.tasks.cashapp.service.CashService;

@Service
public class CashServiceImpl implements CashService {

    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientManager manager;

    public CashServiceImpl(RestTemplate restTemplate, OAuth2AuthorizedClientManager manager) {
        this.restTemplate = restTemplate;
        this.manager = manager;
    }

    @Override
    public void changeCash(CashDto cashDto) {
        String accessToken = getOauth2Token();

        RequestEntity<CashDto> requestEntity = RequestEntity
                .post("http://accounts-app/change-cash")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(cashDto);

        ResponseEntity<?> response = restTemplate.exchange(requestEntity, Object.class);
    }

    private String getOauth2Token() {
        OAuth2AuthorizedClient client = manager.authorize(OAuth2AuthorizeRequest
                .withClientRegistrationId("cash-app")
                .principal("system")
                .build()
        );

        return client.getAccessToken().getTokenValue();
    }
}
