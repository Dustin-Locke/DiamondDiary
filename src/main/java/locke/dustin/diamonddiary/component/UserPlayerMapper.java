package locke.dustin.diamonddiary.component;

import locke.dustin.diamonddiary.dto.join.CreateUserPlayerRequest;
import locke.dustin.diamonddiary.dto.join.UserPlayerResponse;
import locke.dustin.diamonddiary.entity.Player;
import locke.dustin.diamonddiary.entity.User;
import locke.dustin.diamonddiary.entity.join.UserPlayer;
import locke.dustin.diamonddiary.repository.UserPlayerRepository;
import locke.dustin.diamonddiary.service.PlayerService;
import locke.dustin.diamonddiary.service.UserService;
import locke.dustin.diamonddiary.util.exception.UserPlayerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPlayerMapper {

    private final UserService          userService;
    private final PlayerService        playerService;
    private final UserPlayerRepository userPlayerRepo;

    public UserPlayer toEntity ( CreateUserPlayerRequest request ) {

        UUID userId   = request.userId( );
        UUID playerId = request.playerId( );

        User   user   = userService.findById( userId );
        Player player = playerService.findById( playerId );

        if ( userPlayerRepo.existsByUser_IdAndPlayer_Id(
                userId,
                playerId ) ) {
            throw new UserPlayerAlreadyExistsException( request );
        } ;

        return new UserPlayer(
                user,
                player );
    }

    public UserPlayerResponse toResponse ( UserPlayer userPlayer ) {

        return new UserPlayerResponse(
                userPlayer.getId( ),
                userPlayer.getUser( ).getId( ),
                userPlayer.getPlayer( ).getId( ) );
    }
}
