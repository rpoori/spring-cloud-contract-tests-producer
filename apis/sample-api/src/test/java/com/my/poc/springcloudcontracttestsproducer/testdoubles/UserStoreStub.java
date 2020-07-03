package com.my.poc.springcloudcontracttestsproducer.testdoubles;

import com.my.poc.user.User;
import com.my.poc.user.UserStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStoreStub implements UserStore {

    private Map<String, List<User>> userMap = new HashMap<>();

    @Override
    public List<User> getUsers(String segment) {
        return this.userMap.get(segment);
    }

    public void stubUserMap(String segment, List<User> users) {
        this.userMap.put(segment, users);
    }
}
