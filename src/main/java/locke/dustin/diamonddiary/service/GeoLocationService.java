package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.component.AddressMapper;
import locke.dustin.diamonddiary.component.GeoLocationMapper;
import locke.dustin.diamonddiary.dto.geolocation.*;
import locke.dustin.diamonddiary.repository.GeoLocationRepository;
import locke.dustin.diamonddiary.util.*;
import locke.dustin.diamonddiary.util.exception.LocationNotFoundException;
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
    private final MapLinkService        mapLinkService;
    private final GeoLocationRepository repo;

    // Look for existing GeoLocation and if it does not exist create a new one
    //and return response dto
    public GeoLocationResponse create (
            GeoLocationRequest geoLocationRequest,
            AddressRequest addressRequest ) {

        Address address = AddressMapper.toEntity( addressRequest );

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

    public GeoLocationResponse update (
            UUID id,
            GeoLocationRequest geoLocationRequest ) {

        String message = "Geo location not found with id " + id;

        GeoLocation location = repo.findById( id )
                                   .orElseThrow( ( ) -> new LocationNotFoundException( message ) );

        Address        oldAddress     = location.getAddress( );
        AddressRequest requestAddress = geoLocationRequest.address( );

        boolean
                addressChanged =
                !oldAddress.equals( AddressMapper.toEntity( requestAddress ) );

        location.setName( geoLocationRequest.name( ) );
        AddressMapper.updateEntity(
                location.getAddress( ),
                requestAddress );

        if ( addressChanged ) {
            Coordinates coordinates =
                    geocodingService.getCoordinates( location.getAddress( ) );

            location.setLatitude( coordinates.latitude( ) );
            location.setLongitude( coordinates.longitude( ) );
        }

        repo.save( location );

        return geoLocationMapper.toResponse( location );
    }

    public MapLinkResponse createMapLink (
            MapLinkRequest request,
            String mapService ) {

        Double latitude  = request.latitude( );
        Double longitude = request.longitude( );

        String mapLink = switch ( mapService ) {
            case "apple" -> mapLinkService.generateAppleMapsLink(
                    latitude,
                    longitude );
            case "waze" -> mapLinkService.generateWazeMapsLink(
                    latitude,
                    longitude );
            default -> mapLinkService.generateGoogleMapsLink(
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

    public List< GeoLocationResponse > findByName ( String name ) {

        return repo.findByNameContainingIgnoreCase( name )
                   .stream( )
                   .map( geoLocationMapper::toResponse )
                   .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > findByStreetNumber ( String streetNumber ) {

        return repo
                .findByAddress_StreetNumberContainingIgnoreCase( streetNumber )
                .stream( )
                .map( geoLocationMapper::toResponse )
                .collect( Collectors.toList( ) );
    }

    public List< GeoLocationResponse > findByStreetName ( String streetName ) {

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

    public GeoLocationResponse findByNameAndAddress ( GeoLocationRequest geoLocationRequest ) {

        Address
                address =
                AddressMapper.toEntity( geoLocationRequest.address( ) );

        GeoLocation location;

        location =
                repo
                        .findByNameAndAddress(
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
                location =
                repo
                        .findByLatitudeAndLongitude(
                                latitude,
                                longitude )
                        .orElseThrow( ( ) -> new LocationNotFoundException(
                                "Location at " +
                                latitude +
                                ", " +
                                longitude +
                                " not found." ) );

        return geoLocationMapper.toResponse( location );
    }

    public Address getAddressByNameAndCoordinates (
            String name,
            Double latitude,
            Double longitude ) {

        GeoLocation location = repo.findByNameAndLatitudeAndLongitude(
                                           name,
                                           latitude,
                                           longitude )
                                   .orElseThrow( ( ) -> new LocationNotFoundException(
                                           name +
                                           " at " +
                                           latitude +
                                           ", " +
                                           longitude +
                                           " not found." ) );

        return location.getAddress( );
    }

    public void deleteById ( UUID id ) {

        repo.findById( id ).orElseThrow( ( ) -> new LocationNotFoundException(
                "Location with id " +
                id +
                " not found." ) );
        repo.deleteById( id );
    }

}
