package filip.janusz.webblog.controller;

import filip.janusz.webblog.domain.Contact;
import filip.janusz.webblog.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/contactbook")
public class ContactController {

    private ContactServiceImpl contactService;

    @Autowired
    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    public String getAllContacts(Model model) {
        List<Contact> contactList = contactService.findAll();
        model.addAttribute("contacts", contactList);
        model.addAttribute("contact", new Contact());
        model.addAttribute("contactId", new Contact());
        return "showAll";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addContact")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        contactService.create(contact);
        return "redirect:/webblog/contacts";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteContact")
    public String deleteContact(@ModelAttribute("contactId") Contact deleteContact) {
        contactService.delete(deleteContact);
        return "redirect:/webblog/contacts";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleted")
    public String deleted(@RequestParam("idContact") String idContact) {
        contactService.deleteContact(idContact);
        return "redirect:/webblog/contacts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") String id) {
        contactService.deleteContact(id);
        return "redirect:/webblog/contacts";
    }
}
