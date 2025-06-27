package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="title",nullable = false,length = 255)
    private String title;
    @Column(name = "content",nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private String category;
    private String tags;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long readTime;
    private String attachments;
    private String status;
}
