package se.kth.iv1201.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Starts recruitment application
 */
@EnableJpaRepositories("se.kth.iv1201.project.repository")
@SpringBootApplication
public class ProjectApplication {

	/**
	 * Starts recruitment application
	 * @param args no cmd line parameters
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
