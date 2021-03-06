package be.ausy;
import static org.assertj.core.api.Assertions.assertThat;
import be.ausy.controller.CoordinateController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AusyApplicationTests {

	@Autowired
	private CoordinateController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
