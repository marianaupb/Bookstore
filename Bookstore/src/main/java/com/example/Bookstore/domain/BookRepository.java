package com.example.Bookstore.domain;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {
	
	static Optional <Book> findById(@Param ("id") long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
 