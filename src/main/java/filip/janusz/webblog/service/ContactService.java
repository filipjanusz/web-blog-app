package filip.janusz.webblog.service;

import filip.janusz.webblog.domain.Contact;

import java.util.List;

public interface ContactService<T> {
    T create(T obj);
    List<T> findAll();
    void delete(Contact id);
    T update(T obj);
}
