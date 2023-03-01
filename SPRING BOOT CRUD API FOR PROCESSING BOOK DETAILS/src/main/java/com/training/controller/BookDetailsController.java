package com.training.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.BookDetails;
import com.training.repository.BookDetailsRepository;

@RestController
@RequestMapping("/api")
public class BookDetailsController {

	@Autowired
	BookDetailsRepository bookDetailsRepository;

	

	@GetMapping("/bookDetails")
	public ResponseEntity<List<BookDetails>> getAllBookDetailss() {
		try {
			List<BookDetails> bookDetails = new ArrayList<BookDetails>();

			bookDetailsRepository.findAll().forEach(bookDetails::add);

			if (bookDetails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bookDetails/{isbn_id}")
	public ResponseEntity<BookDetails> getBookDetailsById(@PathVariable("isbn_id") long isbn_id) {
		BookDetails bookDetails = bookDetailsRepository.findById(isbn_id);

		if (bookDetails != null) {
			return new ResponseEntity<>(bookDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/bookDetails")
	public ResponseEntity<String> createBookDetails(@RequestBody BookDetails bookDetails) {
		try {
			bookDetailsRepository.save(new BookDetails(bookDetails.getIsbnId(), bookDetails.getBookName(),
					bookDetails.getAuthorName(), bookDetails.getPublicationName(), bookDetails.getYearPublished()));
			return new ResponseEntity<>("BookDetails was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/bookDetails/{isbn_id}")
	public ResponseEntity<String> updateBookDetails(@PathVariable("isbn_id") long isbn_id,
			@RequestBody BookDetails bookDetails) {
		BookDetails _bookDetails = bookDetailsRepository.findById(isbn_id);

		if (_bookDetails != null) {
			_bookDetails.setIsbnId(isbn_id);
			_bookDetails.setBookName(bookDetails.getBookName());
			_bookDetails.setAuthorName(bookDetails.getAuthorName());
			_bookDetails.setPublicationName(bookDetails.getPublicationName());
			_bookDetails.setYearPublished(bookDetails.getYearPublished());

			bookDetailsRepository.update(bookDetails);
			return new ResponseEntity<>("BookDetails was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find BookDetails with id=" + isbn_id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/bookDetails/{isbn_id}")
	public ResponseEntity<String> deleteBookDetails(@PathVariable("isbn_id") long isbn_id) {
		try {
			int result = bookDetailsRepository.deleteById(isbn_id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot find BookDetails with id=" + isbn_id, HttpStatus.OK);
			}
			return new ResponseEntity<>("BookDetails was deleted successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete bookDetails.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/bookDetails")
	public ResponseEntity<String> deleteAllBookDetailss() {
		try {
			int numRows = bookDetailsRepository.deleteAll();
			return new ResponseEntity<>("Deleted " + numRows + " BookDetails(s) successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete bookDetailss.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	}

