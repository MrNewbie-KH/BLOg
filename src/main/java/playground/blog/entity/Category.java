package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToMany
    @JoinTable(
        name="categories_posts",
        joinColumns=@JoinColumn(name = "category_id"),
        inverseJoinColumns=@JoinColumn(name = "article_id")
    )
    private List<Article> articles;

}
