package com.training.repository;

import java.util.List;

import com.training.model.BookDetails;

public interface BookDetailsRepository {

	int save(BookDetails book);

	int update(BookDetails book);

	BookDetails findById(Long id);

	int deleteById(Long id);

	List<BookDetails> findAll();

	int deleteAll();

}