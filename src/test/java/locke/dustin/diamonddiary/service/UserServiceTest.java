package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.dto.user.CreateUserRequest;
import locke.dustin.diamonddiary.dto.user.UserResponse;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.repository.UserRepository;
import locke.dustin.diamonddiary.util.exception.UserAlreadyExistsException;
import locke.dustin.diamonddiary.util.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class )
public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test()
    void shouldCreateUserSuccessfully ( ) {

        CreateUserRequest request =
                new CreateUserRequest(
                        "test@email.com",
                        "password" );

        when( repo.existsByEmail( "test@email.com" ) ).thenReturn( false );
        when( passwordEncoder.encode( "password" ) ).thenReturn(
                "hashedPassword" );

        User savedUser = new User( );
        savedUser.setEmail( "test@email.com" );
        savedUser.setPasswordHash( "hashedPassword" );

        when( repo.save( any( User.class ) ) ).thenReturn( savedUser );

        UserResponse response = userService.create( request );

        assertEquals(
                "test@email.com",
                response.email( ) );

        verify( passwordEncoder ).encode( "password" );
        verify( repo ).save( any( User.class ) );
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists ( ) {

        CreateUserRequest request =
                new CreateUserRequest(
                        "test@email.com",
                        "password" );

        when( repo.existsByEmail( "test@email.com" ) ).thenReturn( true );

        assertThrows(
                UserAlreadyExistsException.class,
                ( ) -> userService.create( request ) );

        verify(
                repo,
                never( ) ).save( any( ) );
    }

    @Test
    void shouldReturnUserById ( ) {

        UUID id = UUID.randomUUID( );

        User user = new User( );
        user.setId( id );
        user.setEmail( "test@email.com" );

        when( repo.findById( id ) ).thenReturn( Optional.of( user ) );

        User result = userService.findById( id );

        assertEquals(
                id,
                result.getId( ) );
    }

    @Test
    void shouldThrowWhenUserNotFound ( ) {

        UUID id = UUID.randomUUID( );

        when( repo.findById( id ) ).thenReturn( Optional.empty( ) );

        assertThrows(
                UserNotFoundException.class,
                ( ) -> userService.findById( id ) );
    }

    @Test
    void shouldDeleteUser() {

        UUID id = UUID.randomUUID();

        User user = new User();
        user.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(user));

        userService.deleteById(id);

        verify(repo).deleteById(id);
    }


}
