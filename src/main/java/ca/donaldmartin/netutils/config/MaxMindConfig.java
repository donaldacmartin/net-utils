package ca.donaldmartin.netutils.config;

import com.maxmind.geoip2.DatabaseReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class MaxMindConfig {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MaxMindConfig.class);

    @Value("${geoip.db.location}")
    private String fileLocation;

    @Bean
    public DatabaseReader databaseReader() {
        DatabaseReader reader = null;

        try {
            File file = Paths.get(fileLocation).toFile();
            reader = new DatabaseReader.Builder(file).build();
            LOGGER.info("Read database stored at {}", fileLocation);
        } catch (IOException e) {
            LOGGER.error("Error reading database: {}", e.getMessage());
        }

        return reader;
    }

}
