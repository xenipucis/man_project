package at.tiby.config;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@NoArgsConstructor
@Getter
@Setter
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix="doge")
public class DogeSettings {
    @Value("${doge.wow}")
	private int wow;
    @Value("${doge.such}")
    private String such;
    @Value("${doge.very}")
    private boolean very;

    
}