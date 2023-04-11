package questapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "quest_body")
    private String questBody;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "done_by_id",
            joinColumns= @JoinColumn(name = "quest_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> doneBy;

    public Quest(User user, String questBody) {
        this.user = user;
        this.questBody = questBody;
        this.status = Status.OPEN;
        user.deductTokensForNewQuest();
    }


    public Quest(Long id, String questBody, Status status) {
        this.id = id;
        this.questBody = questBody;
        this.status = status;
    }

    public Quest() {
        this.status = Status.OPEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestBody() {
        return questBody;
    }

    public void setQuestBody(String questBody) {
        this.questBody = questBody;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<User> getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(List<User> doneBy) {
        this.doneBy = doneBy;
    }
}
