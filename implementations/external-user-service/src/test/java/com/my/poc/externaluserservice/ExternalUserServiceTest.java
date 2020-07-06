package com.my.poc.externaluserservice;

import com.my.poc.externaluserservice.UserInfoResponse.UserInfo;
import com.my.poc.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ExternalUserServiceTest {

    private ExternalUserService externalUserService;
    private RestTemplate restTemplateMock;
    private ExternalUserServiceConfig externalUserServiceConfigMock;

    @Before
    public void setup() {
        restTemplateMock = mock(RestTemplate.class);
        externalUserServiceConfigMock = mock(ExternalUserServiceConfig.class);

        externalUserService = new ExternalUserService(restTemplateMock, externalUserServiceConfigMock);
    }

    @Test
    public void shouldReturnUsersForSegment() {

        // Assign

        String segment = "MILLENIAL";

        given(externalUserServiceConfigMock.getRestApiUrl()).willReturn("http://dummy-url");

        URI uri = UriComponentsBuilder.fromHttpUrl(externalUserServiceConfigMock.getRestApiUrl())
                .pathSegment("{segment}", "users")
                .buildAndExpand(segment)
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                httpHeaders,
                HttpMethod.GET,
                uri
        );

        ResponseEntity responseEntityMock = mock(ResponseEntity.class);

        given(restTemplateMock.exchange(requestEntity, UserInfoResponse.class))
                .willReturn(responseEntityMock);

        given(responseEntityMock.getBody()).willReturn(
                UserInfoResponse.builder()
                        .userInfoList(asList(
                                UserInfo.builder()
                                        .id("user-id-1")
                                        .name("user-name-1")
                                        .email("user-email-1@test.com")
                                        .status("ACTIVE")
                                        .build(),
                                UserInfo.builder()
                                        .id("user-id-2")
                                        .name("user-name-2")
                                        .email("user-email-2@test.com")
                                        .status("PENDING")
                                        .build(),
                                UserInfo.builder()
                                        .id("user-id-3")
                                        .name("user-name-3")
                                        .email("user-email-3@test.com")
                                        .status("ACTIVE")
                                        .build(),
                                UserInfo.builder()
                                        .id("user-id-4")
                                        .name("user-name-4")
                                        .email("user-email-4@test.com")
                                        .status("INACTIVE")
                                        .build()
                        ))
                        .build()
        );

        // Act

        List<User> response = externalUserService.getUsers(segment);

        // Assert

        User expectedUser1 = User.builder()
                .id("user-id-1")
                .name("user-name-1")
                .status("ACTIVE")
                .build();

        User expectedUser2 = User.builder()
                .id("user-id-2")
                .name("user-name-2")
                .status("PENDING")
                .build();

        User expectedUser3 = User.builder()
                .id("user-id-3")
                .name("user-name-3")
                .status("ACTIVE")
                .build();

        User expectedUser4 = User.builder()
                .id("user-id-4")
                .name("user-name-4")
                .status("INACTIVE")
                .build();

        assertThat(response).containsExactlyInAnyOrder(expectedUser1, expectedUser2, expectedUser3, expectedUser4);
    }

}
