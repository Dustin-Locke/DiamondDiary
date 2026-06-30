package locke.dustin.diamonddiary.util.exception;

import java.util.UUID;

public class UserPlayerNotFoundException extends RuntimeException {

    public UserPlayerNotFoundException (
            UUID userId,
            UUID playerId ) {

        super( "User(" +
               userId +
               ") and player(" +
               playerId +
               " union not found." );
    }
}
