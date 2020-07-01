package com.my.poc.user;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetActiveUsersTest {

    private GetActiveUsers getActiveUsers;
    private UserStore userStoreMock;

    private User user1 = User.builder()
            .id("user-id-1")
            .name("user-name-1")
            .status("ACTIVE")
            .build();

    private User user2 = User.builder()
            .id("user-id-2")
            .name("user-name-2")
            .status("PENDING")
            .build();

    private User user3 = User.builder()
            .id("user-id-3")
            .name("user-name-3")
            .status("ACTIVE")
            .build();

    private User user4 = User.builder()
            .id("user-id-4")
            .name("user-name-4")
            .status("INACTIVE")
            .build();

    @Before
    public void setup() {
        userStoreMock = mock(UserStore.class);
        getActiveUsers = new GetActiveUsers(userStoreMock);
    }

    @Test
    public void shouldReturnActiveUsers() {

        // Assign

        String segment = "MILLLENIAL";

        given(userStoreMock.getUsers(segment))
                .willReturn(asList(user1, user2, user3, user4));

        // Act

        List<User> response = getActiveUsers.execute(segment);

        // Assert

        assertThat(response).containsExactlyInAnyOrder(user1, user3);
    }
}
