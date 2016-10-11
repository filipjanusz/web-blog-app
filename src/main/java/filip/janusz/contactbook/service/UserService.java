package filip.janusz.contactbook.service;

import filip.janusz.contactbook.domain.User;
import filip.janusz.contactbook.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDTO dto);
    User findUserByUsername(String username);
    boolean checkIfUserExist(String username);
    User findUserById(String userId);
    List<User> findAllUsers();
    Page<User> findAllUsersOrderByUsername(Pageable pageable);
    void deleteUserById(String userId);
}

