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
//============    attributes    =============
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="title",nullable = false,length = 255)
    private String title;
    @Column(name = "content",nullable = false)
    private String content;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long readTime;
    private List<String> attachments;
    private String status;
//============    Relationships    =============
//============  many to many    ===============
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name= "categories_articles",
            joinColumns=@JoinColumn(name = "article_id"),
            inverseJoinColumns=@JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name= "tags_articles",
            joinColumns=@JoinColumn(name = "article_id"),
            inverseJoinColumns=@JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "groups_articles",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<GroupOfArticles>groups;
//============  One to many    ===============
    @OneToMany(mappedBy = "article" , cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Like> likes;
    @OneToMany(mappedBy = "article", cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments;
//============  many to One    ===============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
//============one to one ============

}
