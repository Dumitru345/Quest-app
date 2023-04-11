package questapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name = "tokens")
	private int tokens = 10;

	@Column(name = "exp")
	private long exp;

	@Column(name = "title")
	@Enumerated(EnumType.STRING)
	private Title title;

	@ManyToMany(mappedBy = "doneBy")
	private List<Quest> questsDone;

	public void deductTokensForNewQuest() {
		if (tokens >= 5) {
			tokens -= 5;
		} else {
			throw new RuntimeException("Insufficient tokens to create new quest");
		}
	}

	// method to add tokens when marking a quest as done
	public void addTokensForQuestDone() {
		tokens += 7;
	}

	public void updateExpAndTitle() {
		if (exp >= 20 && title == Title.BRONZE) {
			title = Title.SILVER;
		}
		if (exp >= 50 && title == Title.SILVER) {
			title = Title.GOLD;
		}
		if (exp >= 100 && title == Title.GOLD) {
			title = Title.DIAMOND;
		}
	}

	public User(Long id, String email, String password, String firstName, String lastName, long exp, Title title) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.exp = exp;
		this.title = title;
	}

	public User() {
		this.title = Title.BRONZE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
}
