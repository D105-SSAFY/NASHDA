package com.ssafy.nashda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //create_on, updata_on 자동 업데이트
@SpringBootApplication   //spring security 비활성화

public class NashdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NashdaApplication.class, args);
    }

}
