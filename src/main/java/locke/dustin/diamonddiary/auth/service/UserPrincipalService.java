package locke.dustin.diamonddiary.auth.service;

import locke.dustin.diamonddiary.auth.component.UserPrincipalFactory;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService
        implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername ( String email ) {

        if ( !userRepo.existsByEmail( email ) ) {
            throw new IllegalArgumentException(
                    "Email address not found." );
        }

        return UserPrincipalFactory.fromUser( new User( email ) );
    }
}
