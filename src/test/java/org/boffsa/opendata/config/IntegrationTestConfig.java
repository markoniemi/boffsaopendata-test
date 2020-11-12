package org.boffsa.opendata.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = { "org.boffsa.opendata" })
public class IntegrationTestConfig {
}
