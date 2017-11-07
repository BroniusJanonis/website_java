package lt.web.service;

import lt.web.models.Users;

public interface IUserService {
    Users saveUser(Users userForm);
    Users firstByUserId(int id);
    void deleteUserByTeacherId(int teacherId);
    void deleteUserByChildId(int childId);
    void deleteUserByFosterId(int fosterId);
}
