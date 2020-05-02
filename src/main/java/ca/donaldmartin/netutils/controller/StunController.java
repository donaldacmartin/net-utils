package ca.donaldmartin.netutils.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/stun")
public class StunController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StunController.class);

    private static final String PROXY_HEADER = "X-Forwarded-For";

    @GetMapping("/get")
    public String get(HttpServletRequest request) {
        String proxyHost = request.getHeader(PROXY_HEADER);
        String remoteHost = request.getRemoteHost();
        String host = proxyHost != null ? proxyHost : remoteHost;
        LOGGER.info("Request from {}", host);
        return host;
    }

}
