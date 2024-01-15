package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private Long id;
    private String text;
    private LocalDateTime creationDate;
    private User author;
    private List<Comment> comments;
}
