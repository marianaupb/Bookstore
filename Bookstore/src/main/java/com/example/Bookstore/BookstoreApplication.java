package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.*;
import com.example.Bookstore.web.*;
import com.example.Bookstore.*;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	} 
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository catrepository, UserRepository urepository) {
		return (args) -> {
			
			Category fiction = catrepository.save(new Category("Fiction"));
			catrepository.save(new Category("Non-Fiction"));
			catrepository.save(new Category("Healthy"));
			Category fantasy = catrepository.save(new Category("Fantasy"));

			brepository.save(new Book("Lewis Carrol", "Alice in Wonderland", "8763-98", 1943, fantasy));
			brepository.save(new Book("Franz Kafka", "The Process", "8765-12", 1912, fiction));
			
			//Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book books : brepository.findAll()) {
				log.info(books.toString());
			}

		};
	}

}