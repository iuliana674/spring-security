package com.example.iuliana.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "post")
@AllArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    @Column(name = "full_text")
    private String fullText;

    @Column(name = "last_modif")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastTimeModified;

    private long views;

    @ManyToOne
    @JoinColumn(name="id_author", nullable=false)
    @JsonIgnoreProperties("posts")
    private User author;

    public Post() {
    }

    public Post(Category category, String title, String fullText, Date lastTimeModified, long views, User author) {
        this.category = category;
        this.title = title;
        this.fullText = fullText;
        this.lastTimeModified = lastTimeModified;
        this.views = views;
        this.author = author;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("Category: %s | Title: %s | Text: %s | Date: %s | Views: %d | Author: %s",
                category, title, fullText, lastTimeModified.toLocalDate().format(formatter), views, author);
    }
}
