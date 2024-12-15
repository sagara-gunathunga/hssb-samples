package com.bpb.hssb.ch4.bookclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bpb.hssb.ch4.bookclub.domain.Book;
import com.bpb.hssb.ch4.bookclub.domain.BookCopy;
import com.bpb.hssb.ch4.bookclub.service.BookService;
import com.bpb.hssb.ch4.bookclub.service.BookServiceException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listAllBooks(Model model) throws BookServiceException {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/{bookId}")
    public String listBook(@PathVariable("bookId") String bookId, Model model) throws BookServiceException {
        model.addAttribute("book", bookService.getBook(bookId));
        return "book";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) throws BookServiceException {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(Book book, RedirectAttributes redirectAttributes) throws BookServiceException {
        bookService.createBook(book);
        redirectAttributes.addFlashAttribute("message",
                "The book " + book.getTitle() + " by " + book.getAuthor() + " has been added");
        return "redirect:/books";
    }

    @PostMapping("remove/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId, RedirectAttributes redirectAttributes)
            throws BookServiceException {
        Book book = bookService.getBook(bookId);
        bookService.removeBook(bookId);
        redirectAttributes.addFlashAttribute("message",
                "The book " + book.getTitle() + " by " + book.getAuthor() + " has been removed");
        return "redirect:/books";
    }

    @PostMapping("{bookId}/add")
    public String addBookCopy(@PathVariable("bookId") String bookId, RedirectAttributes redirectAttributes)
            throws BookServiceException {
        Book book = bookService.getBook(bookId);
        BookCopy copy = bookService.createBookCopy(book);
        redirectAttributes.addFlashAttribute("message",
                "The book copy " + copy.getCopyId() + "has been added");
        return "redirect:/books/" + bookId;

    }

    @PostMapping("{bookId}/remove/{copyId}")
    public String deleteBookCopy(@PathVariable("bookId") String bookId, @PathVariable("copyId") String copyId,
            RedirectAttributes redirectAttributes) throws BookServiceException {

        Book book = bookService.getBook(bookId);
        bookService.removeBookCopy(bookId, copyId);
        redirectAttributes.addFlashAttribute("message",
                "The book copy " + copyId + "has been removed");
        return "redirect:/books/" + bookId;
    }

}