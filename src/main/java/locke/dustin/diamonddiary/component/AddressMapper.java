package locke.dustin.diamonddiary.component;

import locke.dustin.diamonddiary.dto.geolocation.AddressRequest;
import locke.dustin.diamonddiary.dto.geolocation.AddressResponse;
import locke.dustin.diamonddiary.util.Address;

public class AddressMapper {

    public static AddressResponse toAddressResponse ( Address address ) {

        return new AddressResponse(
                address.getStreetNumber( ),
                address.getStreetName( ),
                address.getApartmentNumber( ),
                address.getCity( ),
                address.getState( ),
                address.getZip( )
        );
    }

    public static Address toEntity ( AddressRequest request ) {

        return new Address(
                request.streetNumber( ),
                request.streetName( ),
                request.apartmentNumber( ),
                request.city( ),
                request.state( ),
                request.zip( )
        );
    }

    public static void updateEntity (
            Address address,
            AddressRequest request ) {

        address.setStreetNumber( request.streetNumber( ) );
        address.setStreetName( request.streetName( ) );
        address.setApartmentNumber( request.apartmentNumber( ) );
        address.setCity( request.city( ) );
        address.setState( request.state( ) );
        address.setZip( request.zip( ) );
    }

}
