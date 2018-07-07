package com.tild.desafio.blog.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Preconditions;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String twitter;
    
    @NotBlank
    private String phone;
    
    @NotBlank
    @Email
    private String email;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
        super();
    }

    public User(String name, String twitter, String phone, String email) {
    	this.name = name;
    	this.twitter = twitter;
    	this.phone = phone;
    	this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getTwitterUrl() {
        return String.format("https://twitter.com/%s", this.getTwitter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(twitter, user.twitter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, twitter);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", twitter=" + twitter + ", phone=" + phone + ", email=" + email
				+ "]";
	}

    
    public boolean isValid() {
    	boolean valid = false;
    	
    	try {
    		Arrays.asList(this.getName(), this.getPhone(), this.getTwitter(), this.getEmail())
    		.forEach(Preconditions::checkNotNull);
			
    		valid = true;
		} catch (Exception e) {
			valid = false;
		}
    	
    	return valid;
    }
    
    
    
}
