package locke.dustin.diamonddiary.util.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "geocoding" )
public record GeocodingProperties(
        String scheme,
        String host,
        String userAgent
) {
}
