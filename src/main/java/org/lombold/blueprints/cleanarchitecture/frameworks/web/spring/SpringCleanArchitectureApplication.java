package org.lombold.blueprints.cleanarchitecture.frameworks.web.spring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.lombold.blueprints.cleanarchitecture.frameworks"})
@EnableJpaRepositories(basePackages = {"org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa"})
@EntityScan(basePackages = {"org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringCleanArchitectureApplication {

    static void main(final String[] args) {
        SpringApplication.run(SpringCleanArchitectureApplication.class, args);
    }

}

