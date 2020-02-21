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
	3) тест с правильным id компании и пустым пользователем
	4) тест без id компании
	*/


	//тест на вхождение пользователя в список (правильные данные)
	//Успех: данные пользователя
	@Test
	public void test1() throws Exception{
		int myCompanyId = 1;

		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/1/users?name=user_1", User.class);

		if (result.getCompanyId() == myCompanyId) {
			if (result.getName() == null) System.out.println("Такого пользователя нет, 404");
			else System.out.println("Success!\nПользователь должен быть: " + result.equalsUser(correct) +
									"\nИмя: " + result.getName() +
									"\nid компании: " + result.getCompanyId());
		}
		else System.out.println("Вы не имеете доступа к этим данным");
	}

	//тест без id компании
	//Успех: Нет доступа
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
		else System.out.println("Success! Вы не имеете доступа к этим данным");
	}

	//тест с правильным id компании и пустым именем пользователем
	//Успех: нет такого пользователя
	@Test
	public void test3() throws Exception{
		int myCompanyId = 1;

		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/1/users?name=", User.class);

		if (result.getCompanyId() == myCompanyId) {
			if (result.getName() == null) System.out.println("Success, 404");
			else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
									"\nИмя: " + result.getName() +
									"\nid компании: " + result.getCompanyId());
		}
		else System.out.println("Вы не имеете доступа к этим данным");
	}

	//тест без id компании
	//Успех: нет доступа, т.к. "не та компания"
	@Test
	public void test4() throws Exception{
		int myCompanyId = 1;

		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/users?name=", User.class);

		if (result.getCompanyId() == myCompanyId) {
			if (result.getName() == null) System.out.println("404");
			else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
									"\nИмя: " + result.getName() +
									"\nid компании: " + result.getCompanyId());
		}
		else System.out.println("Success! Вы не имеете доступа к этим данным");
	}
}
