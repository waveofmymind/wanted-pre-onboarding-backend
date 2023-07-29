package waveofmymind.wanted.global.auth;

import waveofmymind.wanted.domain.user.domain.User;

public class UserContext {
    public static ThreadLocal<User> currentUser = new ThreadLocal<>();
}
