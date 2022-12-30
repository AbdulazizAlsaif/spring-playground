package dev.certificate.playground;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PlaygroundApplicationTests {


	@Test
	void contextLoads(ApplicationContext applicationContext) {
		assertNotNull(applicationContext);

	}

}
