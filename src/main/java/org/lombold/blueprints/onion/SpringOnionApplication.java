package org.lombold.blueprints.onion;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringOnionApplication {

    static void main(final String[] args) {
        SpringApplication.run(SpringOnionApplication.class, args);
    }

}
