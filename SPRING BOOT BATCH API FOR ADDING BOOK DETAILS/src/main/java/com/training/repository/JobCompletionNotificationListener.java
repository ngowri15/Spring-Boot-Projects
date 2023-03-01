package com.training.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.training.model.BookDetails;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	private final JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED !! It's time to verify the results!!");

			List<BookDetails> results = jdbcTemplate.query(
					"SELECT ISBN_ID, BOOK_NAME, AUTHOR_NAME, PUBLICATION_NAME, YEAR_PUBLISHED FROM test.BOOK_DETAILS",
					new RowMapper<BookDetails>() {

						@Override
						public BookDetails mapRow(ResultSet rs, int row) throws SQLException {
							return new BookDetails(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
									rs.getInt(5));
						}
					});

			for (BookDetails bookDetail : results) {
				log.info("Found <" + bookDetail + "> in the database.");
			}
		}
	}

}