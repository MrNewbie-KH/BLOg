package playground.blog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import playground.blog.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig   {
    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService() {
//        this whole thing can be replaced by lambda
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user with email "+username+ " not found"));
            }
        };
    }
}
