package cl.ionix.test.restApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
	private Long userId;
	
	private String name;
	
	@Column(name="username", nullable = false)
	private String userName;
	
	@Column(name="email", nullable = false)
	private String email;
	
	private Long phone;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
    public String toString() {
        return "User{" +
                "userId:" + userId +
                ", name:'" + name + '\'' +
                ", userName:'" + userName + '\'' +
                ", email:'" + email + '\'' +
                ", phone:'" + phone + '\'' +
                '}';
    }

}
