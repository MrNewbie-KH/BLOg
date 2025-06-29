package playground.blog.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group_of_articles")
public class GroupOfArticles {
//=========== attributes ==============
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
//=========== relationships ===========

//=========== one to many =============
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
            (
            name = "groups_articles",
            joinColumns = @JoinColumn(name ="group_id" ),
            inverseJoinColumns = @JoinColumn(name = "article_id")
            )
    private List<Article>articles;
//=========== many to many ============
//=========== many to ine =============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//=========== one to one ==============




}
