package at.tiby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import at.tiby.service.DogeService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private DogeService dogeService;

	@Override
	public void run(String... args) {
		System.out.println("AutoConfiguration should have wired up our stuff");
        System.out.println("Let's see if we are doge-worthy...");
        if (dogeService.requiresDogeness()) {
            System.out.println(dogeService.leDoge());
        } else {
            System.out.println("No Doge for us :(");
        }
	}
}
