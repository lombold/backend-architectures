package org.lombold.blueprints.hexagonal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringHexagonalApplication {

    static void main(final String[] args) {
        SpringApplication.run(SpringHexagonalApplication.class, args);
    }

}
