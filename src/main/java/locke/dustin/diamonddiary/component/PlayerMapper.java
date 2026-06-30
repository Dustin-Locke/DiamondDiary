package locke.dustin.diamonddiary.component;

import locke.dustin.diamonddiary.dto.player.CreatePlayerRequest;
import locke.dustin.diamonddiary.dto.player.PlayerResponse;
import locke.dustin.diamonddiary.entity.Player;

public class PlayerMapper {

    public static Player toEntity ( CreatePlayerRequest request ) {

        Player player = new Player( );
        player.setFirstName( request.firstName( ) );
        player.setLastName( request.lastName( ) );
        player.setBirthDate( request.birthDate( ) );
        player.setPreferredJerseyNumber( request.preferredJerseyNumber( ) );
        player.setPrimaryPosition( request.primaryPosition( ) );
        player.setSecondaryPosition( request.secondaryPosition( ) );

        return player;
    }

    public static PlayerResponse toResponse ( Player player ) {

        return new PlayerResponse(
                player.getId( ),
                player.getFirstName( ),
                player.getLastName( ),
                player.getBirthDate( ),
                player.getPreferredJerseyNumber( ),
                player.getPrimaryPosition( ),
                player.getSecondaryPosition( ) );
    }
}
