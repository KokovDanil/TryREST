package TryREST.controller;

import TryREST.exception.NotFoundException;
import TryREST.models.User;
import TryREST.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("company/{companyId}/users")
    public User getUsers(
            @PathVariable int companyId,
            @RequestParam(value = "name") String userName
    ) {
        return userService
                .findUser(companyId, userName)
                .orElseThrow(NotFoundException::new);
    }
}
