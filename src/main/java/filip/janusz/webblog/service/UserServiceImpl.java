package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.User;
import filip.janusz.webblog.dto.UserDTO;
import filip.janusz.webblog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setRole(dto.getRole());
        return userRepo.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean checkIfUserExist(String username) {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onField("username")
                .build();
        mongoTemplate.indexOps(User.class).ensureIndex(textIndex);

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(username);

        Query query = TextQuery.queryText(textCriteria);

        if(mongoTemplate.exists(query, User.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUserById(String userId) {
        return userRepo.findOne(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUserById(String userId) {
        userRepo.delete(userId);
    }

    @Override
    public Page<User> findAllUsersOrderByUsername(Pageable pageable) {
        final PageRequest pageRequest = new PageRequest(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                new Sort(new Sort.Order(Sort.Direction.ASC, "username")));
        return userRepo.findAll(pageRequest);
    }
}
