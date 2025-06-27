package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.List;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "parent")
    private List<Comment> replies;
    @ManyToOne
    @JoinColumn(name = "parent_id",nullable = true)
    private Comment parent;
//    in case of comment written by 1 user
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
//    comment belong to 1 article
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

}
