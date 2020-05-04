package ca.donaldmartin.netutils;

import ca.donaldmartin.netutils.config.JsonConfig;
import ca.donaldmartin.netutils.config.MaxMindConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {JsonConfig.class, MaxMindConfig.class})
public class NetUtilsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NetUtilsApplication.class, args);
    }

}
