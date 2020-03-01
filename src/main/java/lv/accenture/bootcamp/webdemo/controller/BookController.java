package lv.accenture.bootcamp.webdemo.controller;

import lv.accenture.bootcamp.webdemo.model.Book;
import lv.accenture.bootcamp.webdemo.repository.BookRepository;
import lv.accenture.bootcamp.webdemo.validation.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller()
public class BookController {

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-index";
    }

    @GetMapping("/books/add")
    public String addBook(Model model) {
        Book emptyBook = new Book();
        emptyBook.setId(-1L);
        model.addAttribute("book", emptyBook);
        return "book-edit";
    }

    @GetMapping("/books/edit/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book requestedBook = bookRepository.findById(id).get();
        model.addAttribute("book", requestedBook);
        return "book-edit";
    }

    @InitBinder
    public void initBookFormValidation(WebDataBinder dataBinder) {
        dataBinder.addValidators(bookValidator);
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @Valid Book updatedBook, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            updatedBook.setId(id);
            return "book-edit";
        }

        if (id.equals(-1L)) {
            updatedBook.setId(null);
        }

        bookRepository.save(updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String searchByTitle(@RequestParam String searchTitle, Model model) {
        model.addAttribute("books", bookRepository.findByTitle(searchTitle));
        return "book-index";
    }
}
