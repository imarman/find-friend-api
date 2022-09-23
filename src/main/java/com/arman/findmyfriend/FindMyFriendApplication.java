package com.arman.findmyfriend;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class FindMyFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindMyFriendApplication.class, args);
    }

}
