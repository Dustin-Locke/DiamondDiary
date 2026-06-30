package locke.dustin.diamonddiary.auth.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import locke.dustin.diamonddiary.auth.service.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal (
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
                                    ) throws ServletException, IOException {

        String authHeader = request.getHeader( "Authorization" );

        if ( authHeader == null || !authHeader.startsWith( "Bearer " ) ) {
            filterChain.doFilter(
                    request,
                    response );
            return;
        }

        String token = authHeader.substring( 7 );
        String email = jwtService.extractEmail( token );

        if ( email != null && SecurityContextHolder
                                      .getContext( ).getAuthentication( ) ==
                              null ) {

            UserDetails
                    userDetails =
                    userDetailsService.loadUserByUsername( email );

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities( )
                    );

            SecurityContextHolder.getContext( ).setAuthentication( authToken );
        }

        filterChain.doFilter(
                request,
                response );
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/auth/")
               || path.startsWith("/swagger-ui/")
               || path.startsWith("/v3/api-docs")
               || path.startsWith("/api-docs");
    }
}
