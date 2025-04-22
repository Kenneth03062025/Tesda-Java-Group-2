package com.app.service.interfaces;

import com.app.model.Response;
import com.app.model.User;

public interface AuthService {

    Response<?> createUserAccount(User user);

    Response<User> logInUserAccount(User user);
}
