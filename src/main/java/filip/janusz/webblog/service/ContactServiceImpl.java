package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.Contact;
import filip.janusz.webblog.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ContactServiceImpl implements ContactService<Contact> {

    private ContactRepo contactRepo;

    @Autowired
    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public Contact create(Contact obj) {
        return contactRepo.save(obj);
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contactList = contactRepo.findAll();
        return convertToDTOs(contactList);
    }

    private List<Contact> convertToDTOs(List<Contact> models) {
        return models.stream().map(this::convertToDTO).collect(toList());
    }

    private Contact convertToDTO(Contact model) {
        Contact contact = new Contact();
        contact.setId(model.getId());
        contact.setEmailAdress(model.getEmailAdress());
        contact.setFirstName(model.getFirstName());
        contact.setLastName(model.getLastName());
        contact.setPhoneNumber(model.getPhoneNumber());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        contactRepo.delete(contact);
    }

    @Override
    public Contact update(Contact obj) {
        return null;
    }

    public void deleteAll() {
        contactRepo.deleteAll();
    }

    public void deleteContact(String id) {
        contactRepo.delete(id);
    }
}
