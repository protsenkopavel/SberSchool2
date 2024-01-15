package service.impl;

import domain.User;
import repository.UserRepo;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User changeSubscription(User channel, User subscriber) {
        return userRepo.save(channel);
    }

    @Override
    public List<User> getSubscribers(User channel) {
        return null;
    }

    @Override
    public User changeSubscriptionStatus(User channel, User subscriber) {
        return null;
    }
}
