package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S5T1SpringBootApiRestCrudMySqlApplication {

	@Bean
	public ModelMapper modelMapper () { return new ModelMapper();}

	public static void main(String[] args) {
		SpringApplication.run(S5T1SpringBootApiRestCrudMySqlApplication.class, args);
	}

}
