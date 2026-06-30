package locke.dustin.diamonddiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DiamondDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiamondDiaryApplication.class, args);
	}

}
