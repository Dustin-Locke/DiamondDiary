package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.AddressMapper;
import locke.dustin.diamonddiary.component.GeoLocationMapper;
import locke.dustin.diamonddiary.dto.geolocation.*;
import locke.dustin.diamonddiary.repository.GeoLocationRepository;
import locke.dustin.diamonddiary.type.MapService;
import locke.dustin.diamonddiary.util.*;
import locke.dustin.diamonddiary.util.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeoLocationService {

    private final GeoLocationMapper     geoLocationMapper;
    private final GeocodingService      geocodingService;
    private final MapLinkGenerator      mapLinkService;
    private final GeoLocationRepository repo;

    // Look for existing GeoLocation and if it does not exist create a new one
    //and return response dto
    public GeoLocationResponse create (
            GeoLocationRequest geoLocationRequest ) {

        AddressRequest addressRequest = geoLocationRequest.address( );
        Address address =
                AddressMapper.toEntity( addressRequest );

        if ( repo.existsByNameAndAddress_StreetNumberAndAddress_StreetName(
                geoLocationRequest.name( ),
                address.getStreetNumber( ),
                address.getStreetName( ) ) ) {
            throw new LocationAlreadyExistsException( geoLocationRequest );

        }

        GeoLocation location = geoLocationMapper.toEntity(
                geoLocationRequest,
                addressRequest );
        Coordinates coordinates =
                geocodingService.getCoordinates( address );

        location.setLatitude( coordinates.latitude( ) );
        location.setLongitude( coordinates.longitude( ) );

        final GeoLocation finalLocation = location;

        location =
                repo
                        .findByNameAndAddress(
                                geoLocationRequest.name( ),
                                address )
                        .orElseGet( ( ) -> repo.save( finalLocation ) );

        return geoLocationMapper.toResponse( location );
    }

    public GeoLocationResponse findByNameAndStreetNumberAndStreetName (
            String name,
            String streetNumber,
            String streetName ) {

        GeoLocation
                geoLocation =
                repo.findByNameAndAddress_StreetNumberAndAddress_StreetName(
                            name,
                            streetNumber,
                            streetName )
                    .orElseThrow( ( ) -> new LocationNotFoundException(
                                          name,
                                          streetNumber,
                                          streetName )
                                );

        return geoLocationMapper.toResponse( geoLocation );
    }

    public GeoLocationResponse update (
            UUID geoLocationUUID,
            GeoLocationRequest geoLocationRequest ) {

        String requestName = geoLocationRequest.name( );

        GeoLocation location =
                findById( geoLocationUUID );

        String oldGeoLocationName = location.getName( );

        Address        oldAddress     = location.getAddress( );
        AddressRequest requestAddress = geoLocationRequest.address( );

        boolean addressChanged =
                !oldAddress.equals( AddressMapper.toEntity( requestAddress ) );
        boolean nameChanged = !oldGeoLocationName.equals( requestName );

        // if the request address is different than the address in the database,
        // change address to the request address
        if ( addressChanged ) {
            Address updatedAddress = AddressMapper.toEntity( requestAddress );

            location.setAddress( updatedAddress );
            Coordinates coordinates =
                    geocodingService.getCoordinates( updatedAddress );

            location.setLatitude( coordinates.latitude( ) );
            location.setLongitude( coordinates.longitude( ) );
        }

        // if the request name is different than the name in the database,
        // change name to the request name
        if ( nameChanged ) {
            location.setName( requestName );
        }

        //If nothing changed, return the geolocation from database
        if ( !addressChanged && !nameChanged ) {
            return geoLocationMapper.toResponse( location );
        }

        repo.save( location );

        return geoLocationMapper.toResponse( location );
    }

    public GeoLocation findById ( UUID geoLocationId ) {

        return repo.findById( geoLocationId ).orElseThrow( () -> new LocationNotFoundException( geoLocationId ) );
    }

    public MapLinkResponse createMapLink (
            MapLinkRequest request,
            MapService mapService ) {

        Double latitude  = request.latitude( );
        Double longitude = request.longitude( );

        String mapLink = switch ( mapService ) {
            case APPLE -> mapLinkService.generateAppleMapsLink(
                    latitude,
                    longitude );
            case WAZE -> mapLinkService.generateWazeMapsLink(
                    latitude,
                    longitude );
            case GOOGLE -> mapLinkService.generateGoogleMapsLink(
                    latitude,
                    longitude );
        };
        return new MapLinkResponse( mapLink );
    }

    public List< GeoLocationResponse > findAllLocations ( ) {

        List< GeoLocation > allLocations = repo.findAll( );

        return allLocations.stream( )
                           .map( geoLocationMapper::toResponse )
                           .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > searchByName ( String name ) {

        return repo.findByNameContainingIgnoreCase( name )
                   .stream( )
                   .map( geoLocationMapper::toResponse )
                   .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > searchByStreetNumber (
            String streetNumber ) {

        return repo
                .findByAddress_StreetNumberContainingIgnoreCase( streetNumber )
                .stream( )
                .map( geoLocationMapper::toResponse )
                .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > searchByStreetName ( String streetName ) {

        return repo.findByAddress_StreetNameContainingIgnoreCase( streetName )
                   .stream( )
                   .map( geoLocationMapper::toResponse )
                   .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > findBySearchTerm ( String searchTerm ) {

        return repo.search( searchTerm )
                   .stream( )
                   .map( geoLocationMapper::toResponse )
                   .collect( Collectors.toList( ) );
    }

    public GeoLocationResponse findByNameAndAddress (
            GeoLocationRequest geoLocationRequest ) {

        Address address =
                AddressMapper.toEntity( geoLocationRequest.address( ) );

        GeoLocation location;

        location = repo.findByNameAndAddress(
                               geoLocationRequest.name( ),
                               address )
                       .orElseThrow( ( ) -> new LocationNotFoundException(
                               "Location does not exist and should be created."
                       ) );

        return geoLocationMapper.toResponse( location );
    }

    public GeoLocationResponse findByCoordinates (
            Double latitude,
            Double longitude ) {

        GeoLocation
                location = repo.findByLatitudeAndLongitude(
                                       latitude,
                                       longitude )
                               .orElseThrow( ( ) -> new LocationNotFoundException(
                                       latitude,
                                       longitude ) );

        return geoLocationMapper.toResponse( location );
    }

    public Address getAddressByNameAndCoordinates (
            String name,
            Double latitude,
            Double longitude ) {

        GeoLocation location =
                repo.findByNameAndLatitudeAndLongitude(
                            name,
                            latitude,
                            longitude )
                    .orElseThrow( ( ) -> new LocationNotFoundException(
                            name,
                            latitude,
                            longitude ) );

        return location.getAddress( );
    }

    public void deleteById ( UUID id ) {

        findById( id );
        repo.deleteById( id );
    }

}
