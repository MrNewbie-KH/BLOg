package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanProperty;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
//=========== attributes ==============
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
//=========== relationships ===========

//=========== one to many =============
//=========== many to many ============
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
        name= "categories_articles",
        joinColumns=@JoinColumn(name = "category_id"),
        inverseJoinColumns=@JoinColumn(name = "article_id")
    )
    private List<Article> articles;
//=========== many to ine =============
//=========== one to one ==============
@PrePersist
public void prePersist(){
    this.createdAt = new Timestamp(System.currentTimeMillis());
    this.updatedAt = new Timestamp(System.currentTimeMillis());
}
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
