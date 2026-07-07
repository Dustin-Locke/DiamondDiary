package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.dto.geolocation.NominatimResponse;
import locke.dustin.diamonddiary.util.Address;
import locke.dustin.diamonddiary.util.Coordinates;
import locke.dustin.diamonddiary.util.config.GeocodingProperties;
import locke.dustin.diamonddiary.util.exception.AddressNotFoundException;
import locke.dustin.diamonddiary.util.exception.GeocodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    private final RestClient restClient;
    private final GeocodingProperties geocodingProperties;

    public Coordinates getCoordinates ( Address address ) {

        try {
            NominatimResponse[] results =
                    restClient.get( )
                              .uri( uriBuilder ->
                                            uriBuilder
                                                    .scheme( geocodingProperties.scheme( ) )
                                                    .host( geocodingProperties.host( ) )
                                                    .path( "/search" )
                                                    .queryParam(
                                                            "q",
                                                            address.toSingleLine( ) )
                                                    .queryParam(
                                                            "format",
                                                            "jsonv2" )
                                                    .queryParam(
                                                            "limit",
                                                            1 )
                                                    .build( ) )
                              .header(
                                      "User-Agent",
                                      geocodingProperties.userAgent( ) )
                              .retrieve( )
                              .body( NominatimResponse[].class );

            if ( results == null || results.length == 0 ) {
                throw new AddressNotFoundException( address );
            }

            return new Coordinates(
                    Double.parseDouble( results[ 0 ].lat( ) ),
                    Double.parseDouble( results[ 0 ].lon( ) )
            );
        } catch ( RestClientException e ) {
            throw new GeocodingException(
                    "Failed to contact geocoding service.",
                    e
            );
        }
    }
}
