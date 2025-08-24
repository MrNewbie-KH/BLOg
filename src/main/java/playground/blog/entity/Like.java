package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","article_id"})
)
public class Like {
//=========== attributes ==============
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//=========== relationships ===========

//=========== one to many =============
//=========== many to many ============
//=========== many to one =============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",nullable = false)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
//=========== one to one ==============


}