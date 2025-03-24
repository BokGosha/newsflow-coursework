package edu.mirea.bokgosha.newsflow.cloud.firebase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cloud.firebase")
public class FirebaseProperties {

    private String serviceAccessKeyPath;
    private String storageBucket;
}
