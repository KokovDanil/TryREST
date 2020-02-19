package MockServer;

import MockServer.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@SpringBootTest
class MockServerTest{
	//Пример запроса:  some_domain.com/company/777/users?name=Izergil
	@Test
	public void testGetMockServer() throws IOException {
		User correctUser = new User("Kostya", 14);

		String url = "http://localhost:8080/company/14/users?name=Kostya";

		URL object = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) object.openConnection();

		connection.setRequestMethod("GET");

		Map<String, String> response = (Map<String, String>) connection.getInputStream();
		User testUser = new User("K", 14);
		Assertions.assertEquals(correctUser, testUser);
	}
}
