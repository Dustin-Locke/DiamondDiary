package locke.dustin.diamonddiary.auth.service;

import locke.dustin.diamonddiary.auth.UserPrincipal;
import locke.dustin.diamonddiary.auth.dto.LoginRequest;
import locke.dustin.diamonddiary.auth.dto.PasswordUpdateRequest;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.repository.UserRepository;
import locke.dustin.diamonddiary.service.UserService;
import locke.dustin.diamonddiary.util.exception.SamePasswordException;
import locke.dustin.diamonddiary.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService     userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository  repo;

    private final AuthenticationManager authenticationManager;
    private final JwtService            jwtService;

    public String login ( LoginRequest request ) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email( ),
                        request.password( )
                )
                                                                );

        UserPrincipal principal = ( UserPrincipal ) auth.getPrincipal( );

        return jwtService.generateToken( principal );
    }

    public void updatePassword (
            UUID userId,
            PasswordUpdateRequest request ) {

        if ( !repo.existsById( userId ) ) {
            throw new UserNotFoundException( userId );
        }

        User user = userService.findById( userId );

        boolean isSamePassword = passwordEncoder.matches(
                request.newPassword( ),
                user.getPasswordHash( )
                                                        );

        if ( isSamePassword ) {
            throw new SamePasswordException(
                    "The new password must not be the same as the old " +
                    "password." );
        }

        user.setPasswordHash( passwordEncoder.encode( request.newPassword( ) ) );

        repo.save( user );
    }

}
