package com.training.repository;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.training.model.BookDetails;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public FlatFileItemReader<BookDetails> reader() {
		FlatFileItemReader<BookDetails> reader = new FlatFileItemReader<BookDetails>();
		reader.setResource(new ClassPathResource("bookDetails.csv"));
		reader.setLineMapper(new DefaultLineMapper<BookDetails>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "isbnId", "bookName", "authorName", "publicationName",
								"yearPublished" });
					}
				});

				setFieldSetMapper(new BeanWrapperFieldSetMapper<BookDetails>() {
					{
						setTargetType(BookDetails.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public BookDetailsItemProcessor processor() {
		return new BookDetailsItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<BookDetails> writer() {
		JdbcBatchItemWriter<BookDetails> writer = new JdbcBatchItemWriter<BookDetails>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BookDetails>());

		writer.setSql(
				"INSERT INTO test.BOOK_DETAILS (ISBN_ID, BOOK_NAME, AUTHOR_NAME, PUBLICATION_NAME, YEAR_PUBLISHED) VALUES (:isbnId, :bookName, :authorName, :publicationName, :yearPublished)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importBookDetailsJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importBookDetailsJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<BookDetails, BookDetails>chunk(10).reader(reader())
				.processor(processor()).writer(writer()).build();
	}
}