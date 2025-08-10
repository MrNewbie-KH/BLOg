package playground.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    final String SECRET_KEY = "u1iX5nTPz3DZTqJZ4QAhgVpD3i7n3O3XgyvmvG0Wn3w=";

    //create key to sign with
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println(keyBytes);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //    starting with extracting all claims
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //    extract the subjectonly
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    //   generateToken
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //    generate simple token
    public String generateSimpleToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }
//    validate a token
    public boolean isTokenValid(String token , UserDetails userDetails) {
        if(!extractUsername(token).equals(userDetails.getUsername()) && ! isTokenExpired(token)) {
            return false;
        }
        return true;
    }
//    validate expiration
    public boolean isTokenExpired(String token ) {
        if(extractExpiration(token).before(new Date(System.currentTimeMillis()))) {
            return false;
        }
        return true;

    }
}