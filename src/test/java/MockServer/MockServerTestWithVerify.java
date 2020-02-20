package MockServer;

import MockServer.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MockServerTestWithVerify {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	/*
	Тесты для запросов:
	1) тест с правильными данными
	2) тест с данными, где пользователь не из этой компании
	*/


	//тест на вхождение пользователя в список (правильные данные)
	@Test
	public void test1() throws Exception{
		int myCompanyId = 1;

		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/1/users?name=user_1", User.class);

		if (result.getCompanyId() == myCompanyId) {
			if (result.getName() == null) System.out.println("Такого пользователя нет, 404");
			else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
									"\nИмя: " + result.getName() +
									"\nid компании: " + result.getCompanyId());
		}
		else System.out.println("Вы не имеете доступа к этим данным");
	}

	//тест с данными, где пользователь не из этой компании
	@Test
	public void test2() throws Exception{
		int myCompanyId = 2;

		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/1/users?name=user_1", User.class);

		if (result.getCompanyId() == myCompanyId) {
			if (result.getName() == null) System.out.println("Такого пользователя нет, 404");
			else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
									"\nИмя: " + result.getName() +
									"\nid компании: " + result.getCompanyId());
		}
		else System.out.println("Вы не имеете доступа к этим данным");
	}
}
