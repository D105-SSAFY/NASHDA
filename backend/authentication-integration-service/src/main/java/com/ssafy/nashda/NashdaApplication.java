package com.ssafy.nashda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing  //create_on, updata_on 자동 업데이트
@SpringBootApplication   //spring security 비활성화
@EnableScheduling   //스케줄링 활성화
@EnableFeignClients // 외부 인터페이스 사용
public class NashdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NashdaApplication.class, args);
    }

}
