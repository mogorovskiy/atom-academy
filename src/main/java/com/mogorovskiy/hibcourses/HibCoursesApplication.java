package com.mogorovskiy.hibcourses;

import com.mogorovskiy.hibcourses.domain.CourseComplexityEnum;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import com.mogorovskiy.hibcourses.domain.entities.CourseEntity;
import com.mogorovskiy.hibcourses.domain.entities.LessonEntity;
import com.mogorovskiy.hibcourses.service.AuthorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibCoursesApplication implements CommandLineRunner {

    private final AuthorService authorService;

    public HibCoursesApplication(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) {
        AuthorEntity author = new AuthorEntity("Petya Petrovich", "petya@p.com");
        CourseEntity course = new CourseEntity("Java Programing 1", "This course about java programing 1.", CourseComplexityEnum.EASY);

        course.addLesson(new LessonEntity("Introducing", "Hello......"));
        course.addLesson(new LessonEntity("Variables", "Variables....."));

        author.addCourse(course);
        authorService.createAuthor(author);
    }

    public static void main(String[] args) {
        SpringApplication.run(HibCoursesApplication.class, args);
    }
}
