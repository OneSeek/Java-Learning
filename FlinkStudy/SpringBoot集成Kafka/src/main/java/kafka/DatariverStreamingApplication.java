package kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DatariverStreamingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatariverStreamingApplication.class, args);
    }
}
