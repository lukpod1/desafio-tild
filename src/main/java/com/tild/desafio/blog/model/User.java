package com.tild.desafio.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Preconditions;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String twitter;
    
    private String phone;
    
    private String email;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
        super();
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

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", twitter='" + twitter + '\'' +
//                '}';
//    }
    
//    public boolean addUser() {
//    	boolean add = false;
//    	
//    	try {
//    		Arrays.asList(this.getName()).forEach(Preconditions::checkNotNull);
//			
//    		add = true;
//		} catch (Exception e) {
//			add = false;
//		}
//    	
//    	return add;
//    }
    
    
    
}
