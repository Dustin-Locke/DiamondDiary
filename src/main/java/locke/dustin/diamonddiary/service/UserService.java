package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.UserMapper;
import locke.dustin.diamonddiary.dto.user.*;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.repository.UserRepository;
import locke.dustin.diamonddiary.util.exception.UserAlreadyExistsException;
import locke.dustin.diamonddiary.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository  repo;
    private final PasswordEncoder passwordEncoder;


    public List< User > findAll ( ) { return repo.findAll( ); }

    public User findById ( UUID id ) {

        return repo.findById( id )
                   .orElseThrow( ( ) -> new UserNotFoundException( id ) );
    }


    public User findByEmail ( String email ) {

        return repo.findByEmail( email )
                   .orElseThrow( ( ) -> new UserNotFoundException( email ) );
    }

    public void deleteById ( UUID id ) {

        User user = findById( id );
        repo.deleteById( user.getId( ) );
    }

    public UserResponse create ( CreateUserRequest request ) {

        if ( repo.existsByEmail( request.email( ) ) ) {
            throw new UserAlreadyExistsException( request );
        }

        User user = UserMapper.toEntity( request );


        String hashedPassword =
                passwordEncoder.encode( request.password( ) );

        user.setEmail( request.email( ) );
        user.setPasswordHash( hashedPassword );

        return UserMapper.toResponse( repo.save( user ) );
    }

    public UserResponse update (
            UUID id,
            UserRequest request ) {

        User user = findById( id );

        UserMapper.updateEntity(
                user,
                request );

        return UserMapper.toResponse( repo.save( user ) );
    }

}
