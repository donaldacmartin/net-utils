package ca.donaldmartin.netutils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Function;

@Service
public class RequestIpFunction implements
        Function<HttpServletRequest, InetAddress> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RequestIpFunction.class);

    private static final String PROXY_HEADER = "X-Forwarded-For";

    @Override
    public InetAddress apply(HttpServletRequest request) {
        InetAddress ipAddress = InetAddress.getLoopbackAddress();

        if (request != null) {
            try {
                String proxyHost = request.getHeader(PROXY_HEADER);
                String remoteHost = request.getRemoteHost();
                String host = proxyHost != null ? proxyHost : remoteHost;
                ipAddress = InetAddress.getByName(host);

                LOGGER.info("Got IP address: {}", ipAddress.getHostAddress());
            } catch (UnknownHostException e) {
                LOGGER.error("Failed to get IP address: {}", e.getMessage());
            }
        }

        return ipAddress;
    }

}
