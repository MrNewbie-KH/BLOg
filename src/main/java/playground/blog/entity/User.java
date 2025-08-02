package playground.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
//=========== attributes ==============

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "user_name",nullable = true,length = 255)
    private String username;
    @Column(name = "password",nullable = false,length = 255)
    private String password;
    @Column(name = "email",nullable = false,unique = true,length = 255)
    private String email;
    @Column(name = "email_verified",nullable = true)
    private boolean emailVerified;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "is_active",nullable = true)
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
    @OneToMany (mappedBy = "author",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore

    private List<Article> articles;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<GroupOfArticles> groups;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
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
    @PrePersist
    public void prePersist() {
        this.createdAt= new Timestamp(System.currentTimeMillis());
        this.updatedAt=new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt=new Timestamp(System.currentTimeMillis());
    }
    @Enumerated(EnumType.STRING)
    Role role;


//    User datails methods implementation

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
