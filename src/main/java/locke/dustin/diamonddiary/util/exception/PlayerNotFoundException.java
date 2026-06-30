package locke.dustin.diamonddiary.util.exception;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException ( UUID id ) {

        super( "Player " +
               id +
               " not found." );
    }
}
