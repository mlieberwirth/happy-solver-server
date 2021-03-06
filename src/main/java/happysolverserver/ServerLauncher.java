package happysolverserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServerLauncher {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ServerLauncher.class).run(args);
	}
}
