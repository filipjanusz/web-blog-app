package filip.janusz.webblog.repo;

import filip.janusz.webblog.domain.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<Contact, String> {
}
