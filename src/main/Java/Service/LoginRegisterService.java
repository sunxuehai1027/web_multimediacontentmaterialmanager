package Service;

import Entity.User;

public interface LoginRegisterService {

    int login(User user);

    int register(User user);
}
