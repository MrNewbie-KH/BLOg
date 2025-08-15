package playground.blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//    private final JWTService jwtService;
//    private final UserDetailsService userDetailsService;
//
////    filter logic comes here
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String token;
////        check auth header existance as a bearer token
//        if(authHeader==null || authHeader.startsWith("Bearer ")==false){
//            filterChain.doFilter(request,response);
//            return;
//        }
////        extract token
//        token = authHeader.substring(7);
////        extract email from the token
//       String email =  jwtService.extractUsername(token);
////       here we look for that user already  has a token "logged in but
////       with no existence in the context holder"
//       if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//           UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//           if(jwtService.isTokenValid(token,userDetails)){
//               UsernamePasswordAuthenticationToken authObject=new UsernamePasswordAuthenticationToken
//                       (
//                               userDetails,
//                               null,
//                               userDetails.getAuthorities()
//                       );
//               authObject.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////               now we update holder
//               SecurityContextHolder.getContext().setAuthentication(authObject);
//           }
//       }
//
//     filterChain.doFilter(request,response);
//
//
//    }
//}

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Skip JWT validation for auth endpoints
        String path = request.getServletPath();
        return path.startsWith("/api/v1/auth/");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String email = jwtService.extractUsername(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authObject =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authObject.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authObject);
            }
        }

        filterChain.doFilter(request, response);
    }
}
