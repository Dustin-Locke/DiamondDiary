package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.PlayerMapper;
import locke.dustin.diamonddiary.component.UserPlayerMapper;
import locke.dustin.diamonddiary.dto.join.*;
import locke.dustin.diamonddiary.dto.player.PlayerResponse;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.entity.join.UserPlayer;
import locke.dustin.diamonddiary.repository.UserPlayerRepository;
import locke.dustin.diamonddiary.util.exception.UserPlayerAlreadyExistsException;
import locke.dustin.diamonddiary.util.exception.UserPlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPlayerService {

    private final UserService          userService;
    private final PlayerService        playerService;

    private final UserPlayerRepository userPlayerRepo;
    private final UserPlayerMapper     userPlayerMapper;

    public UserPlayer findByUserIdAndPlayerId (
            UUID userId,
            UUID playerId ) {

        if ( !userPlayerRepo.existsByUser_IdAndPlayer_Id(
                userId,
                playerId ) ) {
            throw new UserPlayerNotFoundException(
                    userId,
                    playerId );
        }

        return userPlayerRepo.findByUserAndPlayer(
                userService.findById( userId ),
                playerService.findById( playerId ) );
    }

    public UserPlayerResponse addPlayerToUser ( CreateUserPlayerRequest request ) {

        if ( userPlayerRepo.existsByUser_IdAndPlayer_Id(
                request.userId( ),
                request.playerId( ) ) ) {
            throw new UserPlayerAlreadyExistsException( request );
        }

        User   user   = userService.findById( request.userId( ) );
        Player player = playerService.findById( request.playerId( ) );

        UserPlayer userPlayer = new UserPlayer( );
        userPlayer.setUser( user );
        userPlayer.setPlayer( player );

        UserPlayer saved = userPlayerRepo.save( userPlayer );

        return userPlayerMapper.toResponse( saved );
    }

    public void removePlayerFromUser ( UserPlayerRequest request ) {

        UserPlayer userPlayer = findByUserIdAndPlayerId(
                request.userId( ),
                request.playerId( ) );
        userPlayerRepo.deleteById( userPlayer.getId( ) );
    }
}
