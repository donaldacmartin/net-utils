package ca.donaldmartin.netutils.geoip;

import ca.donaldmartin.netutils.model.GeoInfoDTO;
import com.maxmind.geoip2.model.CityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.function.BiFunction;

@Service
public class CityResponseTransformer implements
        BiFunction<InetAddress, CityResponse, GeoInfoDTO> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CityResponseTransformer.class);

    @Override
    public GeoInfoDTO apply(InetAddress ipAddress, CityResponse cityResponse) {
        GeoInfoDTO geoInfo = null;

        if (ipAddress != null && cityResponse != null) {
            String ipAddr = ipAddress.getHostAddress();
            String city = cityResponse.getCity().getName();
            String country = cityResponse.getCountry().getName();

            geoInfo = new GeoInfoDTO(ipAddr, city, country);
        } else {
            LOGGER.error("Cannot convert null object");
        }

        return geoInfo;
    }
}
