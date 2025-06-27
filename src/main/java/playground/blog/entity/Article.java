package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

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
//    the author of the article
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;


//    ========================================
    @ManyToMany
    @JoinTable(
            name="categories_articles",
            joinColumns=@JoinColumn(name = "article_id"),
            inverseJoinColumns=@JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name="tags_articles",
            joinColumns=@JoinColumn(name = "article_id"),
            inverseJoinColumns=@JoinColumn(name = "tag_id")
    )

    private List<Tag> tags;
    //    ========================================
    @OneToMany(mappedBy = "like")
    private List<Like> likes;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long readTime;
    private List<String> attachments;
    private String status;
}
