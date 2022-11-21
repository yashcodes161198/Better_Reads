package io.yash.betterreadsdataloader;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import io.yash.betterreadsdataloader.author.Author;
import io.yash.betterreadsdataloader.author.AuthorRepository;
import io.yash.betterreadsdataloader.conection.DataStacksAstraProperties;
// /home/yash/Desktop/angela/betterReads/better-reads-data-loader/src/main/java/io/yash/betterreadsdataloader/conection/DataStacksAstraProperties.javaimport io.yash.betterreadsdataloader.connection.DataStaxAstraProperties;


@SpringBootApplication
@EnableConfigurationProperties(DataStacksAstraProperties.class)
public class BetterReadsDataLoaderApplication {

    @Lazy
    @Autowired 
    AuthorRepository authorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
	}

    @PostConstruct
    public void start(){

        Author author = new Author();
        author.setId("id");
        author.setName("yash");
        author.setPersonalName("bhaisa");
        authorRepository.save(author);
        // System.out.println("Application Started 123123123123123**********");
    }









	@Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStacksAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

}
