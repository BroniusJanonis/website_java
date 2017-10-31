package lt.web.service;

import lt.web.models.Users;

public interface IUserService {
    void saveUser(Users userForm);
    Users firstByUserId(int id);
}
