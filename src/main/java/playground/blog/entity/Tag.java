package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany
    @JoinTable(
            name="tags_articles",
            joinColumns=@JoinColumn(name = "tag_id"),
            inverseJoinColumns=@JoinColumn(name = "article_id")
    )
    private List<Article> articles;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
