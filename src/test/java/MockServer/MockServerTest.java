package MockServer;

import MockServer.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MockServerTest{
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void someTest() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:8080/company/1/users?name=user_1", User.class);

		System.out.println(equalsUser(correct, result));
	}

	public boolean equalsUser (User user1, User user2){
		if (!user1.getName().equals(user2.getName())) return false;
		if (user1.getCompanyId() != user2.getCompanyId()) return false;
		return true;
	}
}
