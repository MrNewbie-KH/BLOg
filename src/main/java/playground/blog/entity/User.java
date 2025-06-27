package playground.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "user_name",nullable = false,unique = true,length = 255)
    private String username;
    @Column(name = "password",nullable = false,length = 255)
    private String password;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "email_verified",nullable = false)
    private boolean emailVerified;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "is_active",nullable = false)
    private boolean isActive;
    @Column(name="profile_picture")
    private String profilePicURL;
    @Column(name = "bio",length = 255)
    private  String bio;
    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at",nullable = false)
    private Timestamp updatedAt;
//    -------------------------------------------
//    articles written by the current user
    @OneToMany (mappedBy = "author")
    private List<Article> articles;
//    comments written by the current user
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
}
