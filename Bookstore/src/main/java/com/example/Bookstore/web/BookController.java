package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired ////it will take care that the repository can be included here
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository catrepository;
	
	//show all books
	@RequestMapping(value = {"/", "/booklist"})
	public String bookList (Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//show the page to add new book
	@RequestMapping(value = "/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("category", catrepository.findAll());
        return "addbook";
    } 
	
	//saving the new book and return to booklist
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book books){
        repository.save(books);
        return "redirect:booklist";
    }    
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
    	repository.deleteById(id);
        return "redirect:../booklist";
    }
			
	

}
