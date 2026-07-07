package locke.dustin.diamonddiary.service;

import locke.dustin.diamonddiary.util.exception.CoordinatesNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class )
class MapLinkGeneratorTest {

    private final MapLinkGenerator mapLinkGenerator =
            new MapLinkGenerator();

    @Test
    void shouldGenerateGoogleMapsLink() {

        Double latitude = 30.3322;
        Double longitude = -81.6557;

        String result = mapLinkGenerator.generateGoogleMapsLink(
                latitude,
                longitude
                                                               );

        assertEquals(
                "https://www.google.com/maps/search/?api=1&query=30.3322,-81.6557",
                result
                    );
    }

    @Test
    void shouldGenerateAppleMapsLink() {

        Double latitude = 30.3322;
        Double longitude = -81.6557;

        String result = mapLinkGenerator.generateAppleMapsLink(
                latitude,
                longitude
                                                              );

        assertEquals(
                "http://maps.apple.com/?ll=30.3322,-81.6557",
                result
                    );
    }

    @Test
    void shouldGenerateWazeMapsLink() {

        Double latitude = 30.3322;
        Double longitude = -81.6557;

        String result = mapLinkGenerator.generateWazeMapsLink(
                latitude,
                longitude
                                                             );

        assertEquals(
                "https://www.waze.com/ul?ll=30.3322%2C-81.6557",
                result
                    );
    }

    @Test
    void shouldThrowWhenLatitudeIsNull() {

        assertThrows(
                CoordinatesNotValidException.class,
                () -> mapLinkGenerator.generateGoogleMapsLink(
                        null,
                        -81.6557
                                                           )
                    );
    }

    @Test
    void shouldThrowWhenLongitudeIsNull() {

        assertThrows(
                CoordinatesNotValidException.class,
                () -> mapLinkGenerator.generateGoogleMapsLink(
                        30.3322,
                        null
                                                           )
                    );
    }

    @Test
    void shouldThrowWhenLatitudeIsOutOfRange() {

        assertThrows(
                CoordinatesNotValidException.class,
                () -> mapLinkGenerator.generateGoogleMapsLink(
                        100.0,
                        -81.6557
                                                           )
                    );
    }

    @Test
    void shouldThrowWhenLongitudeIsOutOfRange() {

        assertThrows(
                CoordinatesNotValidException.class,
                () -> mapLinkGenerator.generateGoogleMapsLink(
                        30.3322,
                        -200.0
                                                           )
                    );
    }
}
