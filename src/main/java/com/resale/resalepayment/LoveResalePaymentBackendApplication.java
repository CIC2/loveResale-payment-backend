package com.resale.resalepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class LoveResalePaymentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveResalePaymentBackendApplication.class, args);
    }

}


