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

//=========== attributes ==============

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
//=========== relationships ===========

//=========== one to many =============
    @OneToMany (mappedBy = "author")
    private List<Article> articles;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<GroupOfArticles> groups;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes;
    //=========== many to many ============
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> followers;
//=========== many to one =============
//=========== one to one ==============



}
