package com.resale.homeflypayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class VsoPaymentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsoPaymentBackendApplication.class, args);
    }

}


