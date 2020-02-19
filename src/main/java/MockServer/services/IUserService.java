package MockServer.services;

import MockServer.models.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findUser(int companyId, String name);
}
