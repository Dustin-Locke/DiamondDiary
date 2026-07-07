package locke.dustin.diamonddiary.util.exception;

import locke.dustin.diamonddiary.dto.geolocation.GeoLocationRequest;

import java.util.UUID;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException ( String message ) {

        super( message );
    }

    public LocationNotFoundException ( UUID id ) {

        super( "Location with id " +
               id +
               " not found." );
    }

    public LocationNotFoundException (
            double latitude,
            double longitude ) {

        super( "Location at " +
               latitude +
               ", " +
               longitude +
               " not found." );
    }

    public LocationNotFoundException (
            String name,
            double latitude,
            double longitude ) {

        super( name +
               " at " +
               latitude +
               ", " +
               longitude +
               " not found." );
    }

    public LocationNotFoundException ( GeoLocationRequest request ) {

        super( request.name( ) +
               " at " +
               request.address( ).streetNumber( ) +
               " " +
               request.address( ).streetName( ) +
               " not found." );
    }

    public LocationNotFoundException (
            String name,
            String streetNumber,
            String streetName ) {

        super( name +
               " at " +
               streetNumber +
               " " +
               streetName +
               " not found." );
    }

}
