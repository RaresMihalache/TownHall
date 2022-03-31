package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private String id;

	@Column
	private String email;

	@Column
	private String username;

	@Column
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<House> houses;

	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password +
				'}';
	}

}
