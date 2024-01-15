package service;

import domain.Comment;
import domain.User;

public interface CommentService {
    public Comment create(Comment comment, User user);
}
