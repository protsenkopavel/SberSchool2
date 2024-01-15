package service;

import domain.Message;
import domain.User;

public interface MessageService {
    public String getContent(Message message);

    public void delete(Message message);

    public Message update(Message messageFromDb, Message message);

    public Message create(Message message, User user);

    public Message findForUser(User user);
}
