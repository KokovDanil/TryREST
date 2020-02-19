package TryREST.services.impl;

import TryREST.models.User;
import TryREST.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final List<User> users;

    public UserServiceImpl() {
        this.users = new ArrayList<>();

        fillUsers();
    }

    public Optional<User> findUser(int companyId, String name) {
        return users
                .stream()
                .filter(user -> user.getCompanyId() == companyId && user.getName().equals(name))
                .findFirst();
    }

    private void fillUsers() {
        for(int i = 0; i < 10; i++) {
            User user = new User("name " + (i + 1), i + 1);
            users.add(user);
        }

    }
}
