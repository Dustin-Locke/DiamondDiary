package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.dto.join.CreateUserPlayerRequest;

public class UserPlayerAlreadyExistsException extends RuntimeException {

    public UserPlayerAlreadyExistsException ( CreateUserPlayerRequest request ) {

        super( "User(" +
               request.userId( ) +
               ") and player(" +
               request.playerId( ) +
               " union already exists." );
    }
}
