package junit5testing.javajunit5testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaJunit5TestingApplication {

    public static void main(String[] args) {


        SpringApplication.run(JavaJunit5TestingApplication.class, args);

        System.out.println("==== Junit testing is here ====== ");
    }

}
