package lv.accenture.bootcamp.webdemo.validation;

import lv.accenture.bootcamp.webdemo.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator  implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if(book.getGenre().equals("XXX")) {
            errors.rejectValue("genre", "restricted");
        }
    }
}
