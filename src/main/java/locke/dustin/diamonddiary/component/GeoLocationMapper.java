package locke.dustin.diamonddiary.component;

import locke.dustin.diamonddiary.dto.geolocation.*;
import locke.dustin.diamonddiary.service.GeocodingService;
import locke.dustin.diamonddiary.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeoLocationMapper {

    private final GeocodingService geocodingService;

    public GeoLocationResponse toResponse ( GeoLocation location ) {

        AddressResponse
                addressResponse =
                AddressMapper.toAddressResponse( location.getAddress( ) );

        return new GeoLocationResponse(
                location.getId( ),
                location.getName( ),
                addressResponse,
                location.getLatitude( ),
                location.getLongitude( )
        );
    }

    public GeoLocation toEntity (
            GeoLocationRequest geoLocationRequest,
            AddressRequest addressRequest ) {

        Address address = AddressMapper.toEntity( addressRequest );

        Coordinates coordinates = geocodingService.getCoordinates( address );
        Double      latitude    = coordinates.latitude( );
        Double      longitude   = coordinates.longitude( );

        return new GeoLocation(
                geoLocationRequest.name( ),
                address,
                latitude,
                longitude
        );
    }
}
