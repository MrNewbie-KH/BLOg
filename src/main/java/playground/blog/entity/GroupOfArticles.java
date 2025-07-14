package playground.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "group_of_articles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupOfArticles {
//=========== attributes ==============
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
//=========== relationships ===========

//=========== one to many =============
    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Article>articles;
//=========== many to many ============
//=========== many to ine =============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//=========== one to one ==============




}
