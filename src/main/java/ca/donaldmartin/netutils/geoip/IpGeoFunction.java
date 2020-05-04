package ca.donaldmartin.netutils.geoip;

import ca.donaldmartin.netutils.model.GeoInfoDTO;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.function.Function;

@Service
public class IpGeoFunction implements Function<InetAddress, GeoInfoDTO> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(IpGeoFunction.class);

    @Autowired
    private DatabaseReader databaseReader;

    @Autowired
    private CityResponseTransformer cityResponseTransformer;

    @Override
    public GeoInfoDTO apply(InetAddress ipAddress) {
        GeoInfoDTO geoInfo = null;

        if (ipAddress != null) {
            try {
                CityResponse response = databaseReader.city(ipAddress);
                geoInfo = cityResponseTransformer.apply(ipAddress, response);
                LOGGER.info("IP {} located in {}", ipAddress, geoInfo.getCity());
            } catch (IOException | GeoIp2Exception e) {
                LOGGER.error("Error getting city: {}", e.getMessage());
            }
        } else {
            LOGGER.error("Cannot get city for a null IP address");
        }

        return geoInfo;
    }
}
