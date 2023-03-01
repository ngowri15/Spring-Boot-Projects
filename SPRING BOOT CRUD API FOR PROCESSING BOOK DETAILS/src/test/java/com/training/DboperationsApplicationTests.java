package com.training;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.controller.BookDetailsController;
import com.training.model.BookDetails;
import com.training.repository.BookDetailsRepository;
import com.training.repository.JdbcBookDetailsRepository;


@ExtendWith(MockitoExtension.class)
class DbOperationApplicationTest {

	@InjectMocks
	BookDetailsController bookDetailsController;

	@Mock
	BookDetailsRepository bookDetailsRepository;

	@Mock
	JdbcBookDetailsRepository jdbcBookDetailsRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	 void getBookDetailsTest() {
		
		//Mockito.when(bookDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		//ResponseEntity<List<BookDetails>> result = bookDetailsController.getAllBookDetailss(null);
		
		Mockito.when(bookDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(jdbcBookDetailsRepository.findAll()).thenReturn(new ArrayList<>());
		
		System.out.println("TESTING VALUE :::::::: " + readFile("getBookDetails.json"));
		
		ResponseEntity<List<BookDetails>> result = bookDetailsController.getAllBookDetailss(readFile("getBookDetails.json"));
		
		assertEquals("SUCCESS", result);
		}
	
	
	private String readFile(String fileName) {
		Path relPath = Paths.get("src", "test", "resources", fileName);
		String content = "";
		try {
			content = Files.lines(relPath).collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}