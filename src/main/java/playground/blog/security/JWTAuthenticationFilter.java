package playground.blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

//    filter logic comes here
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
//        check auth header existance as a bearer token
        if(authHeader==null || authHeader.startsWith("Bearer ")==false){
            filterChain.doFilter(request,response);
            return;
        }
//        extract token
        token = authHeader.substring(7);
//        extract email from the token
       String email =  jwtService.extractUsername(token);
       if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){

       }



    }
}
