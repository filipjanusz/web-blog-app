package filip.janusz.contactbook.service;

import filip.janusz.contactbook.domain.CurrentUser;
import filip.janusz.contactbook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new CurrentUser(user);
        }

    }
}
