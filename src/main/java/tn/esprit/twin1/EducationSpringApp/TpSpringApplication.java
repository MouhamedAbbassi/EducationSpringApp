package tn.esprit.twin1.EducationSpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class TpSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSpringApplication.class, args);
	}

}
