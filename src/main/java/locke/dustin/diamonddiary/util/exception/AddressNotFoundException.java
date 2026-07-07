package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.util.Address;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException ( Address address ) {

        super( "Address " +
               address.toSingleLine( ) +
               " not found." );
    }
}
