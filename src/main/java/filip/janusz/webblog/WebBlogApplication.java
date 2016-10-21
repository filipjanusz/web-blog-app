package filip.janusz.webblog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class WebBlogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}

}
