package com.ideas2it.ideasbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    Long reaction;
    Long comment;

    public Post(Long id, String content, Long reaction, Long comment) {
        this.id = id;
        this.content = content;
        this.reaction = reaction;
        this.comment = comment;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReaction() {
        return reaction;
    }

    public void setReaction(Long reaction) {
        this.reaction =reaction;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }
}
