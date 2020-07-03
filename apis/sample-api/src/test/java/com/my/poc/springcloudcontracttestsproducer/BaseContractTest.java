package com.my.poc.springcloudcontracttestsproducer;

import com.my.poc.springcloudcontracttestsproducer.testdoubles.UserStoreStub;
import com.my.poc.user.User;
import com.my.poc.user.UserStore;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringCloudContractTestsProducerApplication.class})
public abstract class BaseContractTest {

    @LocalServerPort
    int port;

    @Autowired
    private UserStore userStore;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + port;
        setUpUserStoreStub();
    }

    private void setUpUserStoreStub() {
        UserStoreStub userStoreStub = (UserStoreStub) userStore;

        User user1 = User.builder()
                .id("user-id-1")
                .name("user-name-1")
                .status("ACTIVE")
                .build();

        User user2 = User.builder()
                .id("user-id-2")
                .name("user-name-2")
                .status("ACTIVE")
                .build();

        User user3 = User.builder()
                .id("user-id-3")
                .name("user-name-3")
                .status("ACTIVE")
                .build();

        userStoreStub.stubUserMap("MILLENIAL", asList(user1, user2, user3));
    }
}
