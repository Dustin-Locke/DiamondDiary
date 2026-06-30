package locke.dustin.diamonddiary.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public record UserPrincipal(
        UUID id,
        String email,
        String passwordHash )

        implements UserDetails {

    @Override
    public String getPassword ( ) {

        return passwordHash;
    }

    @Override
    public String getUsername ( ) {

        return email;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities ( ) {

        return Collections.emptyList( );
    }

}
