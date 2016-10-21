package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.User;
import filip.janusz.webblog.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(UserDTO dto);
    User findUserByUsername(String username);
    boolean checkIfUserExist(String username);
    User findUserById(String userId);
    List<User> findAllUsers();
    Page<User> findAllUsersOrderByUsername(Pageable pageable);
    void deleteUserById(String userId);
}

