package org.lombold.blueprints.cleanenough;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringCleanArchitectureApplication {

    static void main(final String[] args) {
        SpringApplication.run(SpringCleanArchitectureApplication.class, args);
    }

}

