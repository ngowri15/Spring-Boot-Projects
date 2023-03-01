package com.training.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.training.model.BookDetails;

@Repository
public class JdbcBookDetailsRepository implements BookDetailsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(BookDetails bookDetails) {
		return jdbcTemplate.update(
				"INSERT INTO BOOK_DETAILS (ISBN_ID, BOOK_NAME, AUTHOR_NAME, PUBLICATION_NAME, YEAR_PUBLISHED) VALUES(?,?,?,?,?)",
				new Object[] { bookDetails.getIsbnId(), bookDetails.getBookName(), bookDetails.getAuthorName(),
						bookDetails.getPublicationName(), bookDetails.getYearPublished() });
	}

	@Override
	public int update(BookDetails bookDetails) {
		return jdbcTemplate.update(
				"UPDATE BOOK_DETAILS SET BOOK_NAME=?, AUTHOR_NAME=?, PUBLICATION_NAME=?, YEAR_PUBLISHED=? WHERE ISBN_ID=?",
				new Object[] { bookDetails.getBookName(), bookDetails.getAuthorName(), bookDetails.getPublicationName(),
						bookDetails.getYearPublished(), bookDetails.getIsbnId() });
	}

	@Override
	public BookDetails findById(Long isbn_id) {
		try {
			BookDetails bookDetails = jdbcTemplate.queryForObject("SELECT * FROM BOOK_DETAILS WHERE ISBN_ID=?",
					BeanPropertyRowMapper.newInstance(BookDetails.class), isbn_id);

			return bookDetails;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(Long isbn_id) {
		return jdbcTemplate.update("DELETE FROM BOOK_DETAILS WHERE ISBN_ID=?", isbn_id);
	}

	@Override
	public List<BookDetails> findAll() {
		return jdbcTemplate.query("SELECT * from BOOK_DETAILS", BeanPropertyRowMapper.newInstance(BookDetails.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE from BOOK_DETAILS");
	}

}