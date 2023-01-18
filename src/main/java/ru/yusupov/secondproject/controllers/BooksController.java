package ru.yusupov.secondproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yusupov.secondproject.models.Book;
import ru.yusupov.secondproject.models.Person;
import ru.yusupov.secondproject.services.BookService;
import ru.yusupov.secondproject.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public BooksController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(@RequestParam(required = false) Integer page,
                        @RequestParam(required = false) Integer books_per_page,
                        @RequestParam(required = false) boolean sort_by_year, Model model) {
        if ((page != null && books_per_page != null) & sort_by_year) model.addAttribute("book",
                bookService.index(page, books_per_page));
        else if (page != null && books_per_page != null) model.addAttribute("book", bookService.index(page, books_per_page));
        else if (sort_by_year) model.addAttribute("book", bookService.index(true));
        else model.addAttribute("book", bookService.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person1") Person person) {
        model.addAttribute("book", bookService.show(id));
        model.addAttribute("persons", peopleService.index());
        model.addAttribute("person", bookService.getOwner(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "books/edit";
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String add(@PathVariable("id") int id, @ModelAttribute("person1") Person person) {
        bookService.updatePerson(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        bookService.deleteUser(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchForm() {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchResult(Model model, @RequestParam("text") String text) {
        model.addAttribute("books", bookService.search(text));
        return "books/search";
    }
}
