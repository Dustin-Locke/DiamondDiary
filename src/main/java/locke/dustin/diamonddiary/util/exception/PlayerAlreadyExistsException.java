package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.dto.player.CreatePlayerRequest;
import locke.dustin.diamonddiary.entity.Player;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException ( CreatePlayerRequest request ) {

        super( " Player named " +
               request.firstName( ) +
               " " +
               request.lastName( ) +
               " (" +
               ChronoUnit.YEARS.between(
                       request.birthDate( ),
                       LocalDate.now( ) ) +
               ") already exists." );

    }

    public PlayerAlreadyExistsException ( Player player ) {

        super( " Player named " +
               player.getFirstName( ) +
               " " +
               player.getLastName( ) +
               " (" +
               ChronoUnit.YEARS.between(
                       player.getBirthDate( ),
                       LocalDate.now( ) ) +
               ") already exists." );

    }

}
