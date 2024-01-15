package domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;
    private Set<User> subscriptions = new HashSet<>();
    private Set<User> subscribers = new HashSet<>();
}
