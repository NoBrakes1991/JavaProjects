package entities;

import javax.persistence.*;

@Entity
@Table (name = "userMessages")

public class UserMessage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    String userMessage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return userMessage;
    }

    public void setMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserMessage(){}

    public UserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
