package ca.donaldmartin.netutils.controller;

import ca.donaldmartin.netutils.geoip.IpGeoFunction;
import ca.donaldmartin.netutils.ip.RequestIpFunction;
import ca.donaldmartin.netutils.model.GeoInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@RestController
@RequestMapping("/stun")
public class StunController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StunController.class);

    @Autowired
    private RequestIpFunction requestIpFunction;

    @Autowired
    private IpGeoFunction ipGeoFunction;

    @GetMapping("/get")
    public InetAddress get(HttpServletRequest request) {
        return requestIpFunction.apply(request);
    }

    @GetMapping("/getInfo")
    public GeoInfoDTO getInfo(HttpServletRequest request) {
        return requestIpFunction.andThen(ipGeoFunction).apply(request);
    }

}
