package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.UserPlayerMapper;
import locke.dustin.diamonddiary.dto.join.*;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.entity.join.UserPlayer;
import locke.dustin.diamonddiary.repository.*;
import locke.dustin.diamonddiary.test_data.GenerateTestEntity;
import locke.dustin.diamonddiary.type.FieldingPosition;
import locke.dustin.diamonddiary.util.embedded_id.UserPlayerId;
import locke.dustin.diamonddiary.util.exception.UserPlayerAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class )
public class UserPlayerServiceTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private UserService userService;

    @Mock
    private UserPlayerMapper userPlayerMapper;

    @Mock
    private UserPlayerRepository userPlayerRepo;

    @InjectMocks
    private UserPlayerService userPlayerService;

    private GenerateTestEntity generate;


    @Test
    void shouldCreateUserPlayerSuccessfully ( ) {

        User   user   = generate.testUser( );
        Player player = generate.testPlayer( );

        UUID userId   = user.getId( );
        UUID playerId = player.getId( );

        UserPlayerId userPlayerId = new UserPlayerId(
                user.getId( ),
                player.getId( ) );

        UserPlayer savedUserPlayer = new UserPlayer( );
        savedUserPlayer.setUser( user );
        savedUserPlayer.setPlayer( player );

        CreateUserPlayerRequest userPlayerRequest =
                new CreateUserPlayerRequest(
                        userId,
                        playerId );

        UserPlayerResponse
                userPlayerResponse =
                new UserPlayerResponse(
                        userPlayerId,
                        user.getId( ),
                        player.getId( ) );

        when( userService.findById( userId ) )
                .thenReturn( user );
        when( playerService.findById( playerId ) )
                .thenReturn( player );
        when( userPlayerMapper.toResponse( savedUserPlayer ) )
                .thenReturn( userPlayerResponse );
        when( userPlayerRepo.existsByUser_IdAndPlayer_Id(
                userId,
                playerId ) )
                .thenReturn( false );
        when( userPlayerRepo.save( any( UserPlayer.class ) ) )
                .thenReturn( savedUserPlayer );

        UserPlayerResponse addedPlayerToUserResponse =
                userPlayerService.addPlayerToUser( userPlayerRequest );

        assertEquals(
                userId,
                addedPlayerToUserResponse.userId( ) );
        assertEquals(
                playerId,
                addedPlayerToUserResponse.playerId( )
                    );

        verify( userService ).findById( userId );
        verify( playerService ).findById( playerId );
        verify( userPlayerMapper )
                .toResponse( savedUserPlayer );
        verify( userPlayerRepo )
                .existsByUser_IdAndPlayer_Id(
                        userId,
                        playerId );
        verify( userPlayerRepo )
                .save( any( UserPlayer.class ) );

    }

    @Test
    void shouldThrowWhenUserPlayerAlreadyExists ( ) {

        UUID userId   = UUID.randomUUID( );
        UUID playerId = UUID.randomUUID( );

        CreateUserPlayerRequest request =
                new CreateUserPlayerRequest(
                        userId,
                        playerId );

        User user = new User( );
        user.setId( userId );

        Player player = new Player( );
        player.setId( playerId );

        when( userService.findById( userId ) )
                .thenReturn( user );
        when( playerService.findById( playerId ) )
                .thenReturn( player );
        when( userPlayerRepo.existsByUser_IdAndPlayer_Id(
                userId,
                playerId ) )
                .thenReturn( true );

        assertThrows(
                UserPlayerAlreadyExistsException.class,
                ( ) -> userPlayerService.addPlayerToUser( request )
                    );

        verify(
                userPlayerRepo,
                never( ) )
                .save( any( UserPlayer.class ) );
    }

    @Test
    void shouldReturnUserPlayerByUserIdAndPlayerId ( ) {

        User user = generate.testUser( );

        Player player = generate.testPlayer( );

        UserPlayerId userPlayerId = new UserPlayerId(
                user.getId( ),
                player.getId( ) );

        UserPlayer savedUserPlayer = new UserPlayer( );
        savedUserPlayer.setId( userPlayerId );
        savedUserPlayer.setUser( user );
        savedUserPlayer.setPlayer( player );

        when( userPlayerService.findByUserIdAndPlayerId(
                user.getId( ),
                player.getId( ) ) )
                .thenReturn( savedUserPlayer );
    }
}
