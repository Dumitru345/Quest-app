package questapp.service;

import questapp.entity.User;

public interface UserService {

    User findByEmail(String email);

}
