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

	/*
	Тесты для запросов:
	1) тест с правильными данными
	2) тест с данными, где в компании нет конкретного пользователя
	3) тест с пустым именем пользователя
	4) тест, в котором вместо id компании строка
	5) тест, в котором нет id компании
	*/


	//Тест на вхождение пользователя в список (правильные данные)
	//Успех: верные данны пользователя
	@Test
	public void test1() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/1/users?name=user_1", User.class);

		if (result.getName() == null) System.out.println("Такого пользователя нет, 404");
		else System.out.println("Success!\nПользователь должен быть: " + result.equalsUser(correct) +
								"\nИмя: " + result.getName() +
								"\nid компании: " + result.getCompanyId());
	}

	//Тест на вхождение пользователя в список (нет пользователя в данной компании)
	//Успех: нет пользователя - 404
	@Test
	public void test2() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/2/users?name=user_1", User.class);

		if (result.getName() == null) System.out.println("Success! Такого пользователя нет, 404");
		else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
								"\nИмя: " + result.getName() +
								"\nid компании: " + result.getCompanyId());
	}

	//тест с пустым именем пользователя
	//Успех: нет пользователя - 404
	@Test
	public void test3() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/2/users?name=", User.class);

		if (result.getName() == null) System.out.println("Success! Такого пользователя нет, 404");
		else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
								"\nИмя: " + result.getName() +
								"\nid компании: " + result.getCompanyId());
	}

	//тест, в котором вместо id компании строка
	//Успех: нет пользователя - 404
	@Test
	public void test4() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/asd/users?name=user_1", User.class);

		if (result.getName() == null) System.out.println("Success, 404");
		else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
								"\nИмя: " + result.getName() +
								"\nid компании: " + result.getCompanyId());
	}

	//тест, в котором нет id компании
	//Успех: 404
	@Test
	public void test5() throws Exception{
		User correct = new User("user_1", 1);

		User result = this.restTemplate.getForObject("http://localhost:"+ port +"/company/users?name=user_1", User.class);

		if (result.getName() == null) System.out.println("Success, 404");
		else System.out.println("Пользователь должен быть: " + result.equalsUser(correct) +
								"\nИмя: " + result.getName() +
								"\nid компании: " + result.getCompanyId());
	}
}
