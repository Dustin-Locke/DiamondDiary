package locke.dustin.diamonddiary.repository;

import locke.dustin.diamonddiary.util.Address;
import locke.dustin.diamonddiary.util.GeoLocation;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface GeoLocationRepository
        extends JpaRepository< GeoLocation, UUID > {

    Optional< GeoLocation > findByLatitudeAndLongitude (
            Double latitude,
            Double longitude );

    Optional< GeoLocation > findByNameAndAddress (
            String name,
            Address address );

    Optional< GeoLocation > findByNameAndLatitudeAndLongitude (
            String name,
            Double latitude,
            Double longitude );

    Optional< GeoLocation > findByNameContainingIgnoreCase ( String name );

    Optional< GeoLocation > findByAddress_StreetNumberContainingIgnoreCase ( String streetNumber );

    Optional< GeoLocation > findByAddress_StreetNameContainingIgnoreCase ( String streetName );

    @Query(
            """
                SELECT g FROM GeoLocation g
                WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(g.address.streetName) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(g.address.streetNumber) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(g.address.city) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(g.address.state) LIKE LOWER(CONCAT('%', :query, '%'))
            """ )
    List< GeoLocation > search (
            @Param("query" ) String query );

    void deleteById ( @NonNull UUID id );
}
