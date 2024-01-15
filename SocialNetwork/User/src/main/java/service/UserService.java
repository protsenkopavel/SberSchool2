package service;

import domain.User;

import java.util.List;

public interface UserService {
    public User changeSubscription(User channel, User subscriber);

    public List<User> getSubscribers(User channel);

    public User changeSubscriptionStatus(User channel, User subscriber);
}
