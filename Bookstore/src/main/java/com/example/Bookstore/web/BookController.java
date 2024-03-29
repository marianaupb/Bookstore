package com.example.Bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired // it will take care that the repository can be included here
	private BookRepository repository;

	@Autowired
	private CategoryRepository catrepository;

	// show all books - login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// show all books
	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	// RESTful service to get book by id into the search bar
	@RequestMapping(value = "/booklist/{id}", method = RequestMethod.GET)
	// optional replace null:
	public String findBookRest(@PathVariable("id") long id, Model model) {
		Optional<Book> searchVar = repository.findById(id);
		if (searchVar.isPresent()) {
			List<Book> bookSearch = new ArrayList<Book>();
			bookSearch.add(searchVar.get());
			model.addAttribute("books", bookSearch);
		}
		return "booklist";
	}

	// show the page to add new book
	@RequestMapping(value = "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("category", catrepository.findAll());
		return "addbook";
	}

	// edit
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editbook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("category", catrepository.findAll());
		return "editbook";
	}
	
	//saving the book edited and return to book list
	@RequestMapping(value = "/saveEdition", method = RequestMethod.POST)
	public String saveEdition(Book books) {
		repository.save(books);
		return "redirect:booklist";
	}

	// saving the new book and return to book list
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book books) {
		repository.save(books);
		return "redirect:booklist";
	}

	// delete
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}

}
