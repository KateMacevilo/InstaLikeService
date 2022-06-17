package com.tms.InstaLike.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private byte[] photo;
    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likeList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(byte[] photo, String description) {
        this.photo = photo;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(Set<Like> likeList) {
        this.likeList = likeList;
    }

    public int getCountLikes(){
       return getLikeList().size();
    }
}
