package com.mogorovskiy.hibcourses;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibCoursesApplication implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    public HibCoursesApplication(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {
        AuthorEntity author = new AuthorEntity("testAuthor1", "fdkjgh@com");
        CourseEntity course = new CourseEntity("TestCourse1", "This is test course 1", CourseComplexityEnum.EASY);

        author.addCourse(course);
        authorRepository.save(author);
    }

    public static void main(String[] args) {
        SpringApplication.run(HibCoursesApplication.class, args);
    }
}
