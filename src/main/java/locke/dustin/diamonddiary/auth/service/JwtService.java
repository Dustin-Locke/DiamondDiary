package locke.dustin.diamonddiary.auth.service;

import locke.dustin.diamonddiary.auth.UserPrincipal;
import locke.dustin.diamonddiary.auth.component.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private Key getSigningKey ( ) {

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode( jwtProperties.secret( ) )
                                 );
    }

    public String generateToken ( UserPrincipal user ) {

        return Jwts.builder( )
                   .setSubject( user.email( ) )
                   .claim(
                           "id",
                           user.id( ) )
                   .setIssuedAt( new Date( ) )
                   .setExpiration( new Date( System.currentTimeMillis( ) +
                                             jwtProperties.expiration( ) ) )
                   .signWith( getSigningKey( ) )
                   .compact( );
    }

    public String extractEmail ( String token ) {

        return Jwts.parserBuilder( )
                   .setSigningKey( jwtProperties.secret( ) )
                   .build( )
                   .parseClaimsJws( token )
                   .getBody( )
                   .getSubject( );
    }
}
