package TryREST.services;

import TryREST.models.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findUser(int companyId, String name);
}
