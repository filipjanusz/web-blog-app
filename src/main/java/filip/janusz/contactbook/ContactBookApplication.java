package filip.janusz.contactbook;

import filip.janusz.contactbook.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class ContactBookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContactBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}

}
