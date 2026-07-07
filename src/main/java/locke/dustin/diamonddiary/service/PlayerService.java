package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.PlayerMapper;
import locke.dustin.diamonddiary.dto.player.CreatePlayerRequest;
import locke.dustin.diamonddiary.dto.player.PlayerResponse;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.repository.PlayerRepository;
import locke.dustin.diamonddiary.util.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepo;

    public Player findById ( UUID id ) {

        return playerRepo.findById( id )
                         .orElseThrow( ( ) -> new PlayerNotFoundException( id ) );
    }

    public PlayerResponse create (
            CreatePlayerRequest request ) {

        if ( playerRepo.existsByFirstNameAndLastNameAndBirthDate(
                request.firstName( ),
                request.lastName( ),
                request.birthDate( ) )
        ) { throw new PlayerAlreadyExistsException( request ); }

        Player player = PlayerMapper.toEntity( request );

        playerRepo.save( player );

        return PlayerMapper.toResponse( player );
    }

    public PlayerResponse create (
            Player player ) {

        if ( playerRepo.existsByFirstNameAndLastNameAndBirthDate(
                player.getFirstName( ),
                player.getLastName( ),
                player.getBirthDate( ) )
        ) { throw new PlayerAlreadyExistsException( player ); }

        playerRepo.save( player );

        return PlayerMapper.toResponse( player );
    }

    public void deleteById ( UUID playerId ) {

        Player user = findById( playerId );
        playerRepo.deleteById( user.getId( ) );
    }
}
