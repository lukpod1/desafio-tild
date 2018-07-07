package com.tild.desafio.blog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Preconditions;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("posts")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Post_Tag", 
            joinColumns = { @JoinColumn(name = "post_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
        )
    private List<Tag> tags;
    
    public Post() {
        super();
        tags = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		Arrays.asList(tags).stream()
		.forEach(tag -> {
			this.tags.add(new Tag(tag));
		});
	}

	@Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }

    public boolean isValid() {
        boolean valid = false;

        try {
            Arrays.asList(this.getUser(), this.getText(), this.getTitle())
                    .forEach(Preconditions::checkNotNull);

            Arrays.asList(this.getText(), this.getTitle())
                    .forEach(txt -> {
                        Preconditions.checkArgument(!txt.isEmpty());
                    });

            valid = true;
        } catch (Exception e){
            valid = false;
        }

        return valid;
    }
}
