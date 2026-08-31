package pl.visa.finalproject;

import org.springframework.boot.SpringApplication;

public class TestFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(FinalProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
