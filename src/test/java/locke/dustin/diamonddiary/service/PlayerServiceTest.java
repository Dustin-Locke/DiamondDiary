package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.dto.player.CreatePlayerRequest;
import locke.dustin.diamonddiary.dto.player.PlayerResponse;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.repository.PlayerRepository;
import locke.dustin.diamonddiary.type.FieldingPosition;
import locke.dustin.diamonddiary.util.exception.PlayerAlreadyExistsException;
import locke.dustin.diamonddiary.util.exception.PlayerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class )
public class PlayerServiceTest {

    @Mock
    private PlayerRepository repo;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void shouldCreatePlayerSuccessfully ( ) {

        when( repo.existsByFirstNameAndLastNameAndBirthDate(
                "Sam",
                "Reich",
                LocalDate.now( ) ) )
                .thenReturn( false );

        Player savedPlayer = new Player( );
        savedPlayer.setFirstName( "Sam" );
        savedPlayer.setLastName( "Reich" );
        savedPlayer.setBirthDate( LocalDate.now( ) );
        savedPlayer.setPreferredJerseyNumber( 99 );
        savedPlayer.setPrimaryPosition( FieldingPosition.CATCHER );
        savedPlayer.setSecondaryPosition( FieldingPosition.CORNERS );

        when( repo.save( any( Player.class ) ) )
                .thenReturn( savedPlayer );

        PlayerResponse response = playerService.create( savedPlayer );

        assertEquals(
                "Sam",
                response.firstName( ) );
        assertEquals(
                "Reich",
                response.lastName( ) );
        assertEquals(
                LocalDate.now( ),
                response.birthDate( ) );


        verify( repo )
                .save( any( Player.class ) );
    }

    @Test
    void shouldThrowExceptionWhenPlayerAlreadyExists ( ) {

        CreatePlayerRequest request = new CreatePlayerRequest(
                "Sam",
                "Reich",
                LocalDate.now( ),
                99,
                FieldingPosition.CATCHER,
                FieldingPosition.CORNERS );

        when( repo.existsByFirstNameAndLastNameAndBirthDate(
                "Sam",
                "Reich",
                LocalDate.now( ) ) ).thenReturn( true );

        assertThrows(
                PlayerAlreadyExistsException.class,
                ( ) -> playerService.create( request ) );

        verify(
                repo,
                never( ) )
                .save( any( ) );
    }

    @Test
    void shouldReturnPlayerById ( ) {

        UUID id = UUID.randomUUID( );

        Player player = new Player( );
        player.setId( id );
        player.setFirstName( "Sam" );
        player.setLastName( "Reich" );
        player.setBirthDate( LocalDate.now( ) );
        player.setPreferredJerseyNumber( 99 );
        player.setPrimaryPosition( FieldingPosition.CATCHER );
        player.setSecondaryPosition( FieldingPosition.CORNERS );

        when( repo.findById( id ) )
                .thenReturn( Optional.of( player ) );

        Player result = playerService.findById( id );

        assertEquals(
                id,
                result.getId( ) );
    }

    @Test
    void shouldThrowWhenPlayerNotFound ( ) {

        UUID id = UUID.randomUUID( );

        when( repo.findById( id ) ).thenReturn( Optional.empty( ) );

        assertThrows(
                PlayerNotFoundException.class,
                ( ) -> playerService.findById( id ) );
    }

    @Test
    void shouldDeletePlayer ( ) {

        UUID id = UUID.randomUUID( );

        Player player = new Player( );
        player.setId( id );

        when( repo.findById( id ) )
                .thenReturn( Optional.of( player ) );

        playerService.deleteById( id );

        verify( repo ).deleteById( id );
    }

}

