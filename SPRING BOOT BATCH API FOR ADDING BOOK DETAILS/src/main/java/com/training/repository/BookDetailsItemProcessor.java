package com.training.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.training.model.BookDetails;

public class BookDetailsItemProcessor implements ItemProcessor<BookDetails, BookDetails> {
	private static final Logger log = LoggerFactory.getLogger(BookDetailsItemProcessor.class);

	@Override
	public BookDetails process(final BookDetails bookDetails) throws Exception {
		final long isbnId = bookDetails.getIsbnId();
		final String bookName = bookDetails.getBookName();
		final String authorName = bookDetails.getAuthorName();
		final String publicationName = bookDetails.getPublicationName();
		final Integer yearPublished = bookDetails.getYearPublished();

		final BookDetails transformedPerson = new BookDetails(isbnId, bookName, authorName, publicationName,
				yearPublished);

		log.info("Converting (" + bookDetails + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}
}