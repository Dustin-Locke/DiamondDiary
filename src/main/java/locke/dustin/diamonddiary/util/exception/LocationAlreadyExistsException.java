package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.dto.geolocation.GeoLocationRequest;

public class LocationAlreadyExistsException extends RuntimeException {

    public LocationAlreadyExistsException ( GeoLocationRequest request ) {
        super( request.name( ) +
               " at " +
               request.address().streetNumber() +
               " " +
               request.address().streetName() +
               " already exists.");
    }
}
