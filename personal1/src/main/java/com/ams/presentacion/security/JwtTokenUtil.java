package com.ams.presentacion.security;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ams.presentacion.User.User;
import com.ams.presentacion.User.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("serial")
@Component
public class JwtTokenUtil implements Serializable{

	//Creador
	private final String ISSUER="ams";
	
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
    	//De la clase Claims utilizas el método getExpiration
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //NUEVO a partir de J8 , el método recibe un parametro string y un método como parámetro,
    //el método apply() aplica el método recibido sobre el objeto indicado
    public  <R, T> R getClaimFromToken(String token, Function<Claims,R> funcionToApply) {
        final Claims claims = getAllClaimsFromToken(token);
        return funcionToApply.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDto user) {
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject) {
    	
    	//Creacion de los claims,parte del payload del token
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("rol",new SimpleGrantedAuthority("ROLE_ADMIN"));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }
	
}
