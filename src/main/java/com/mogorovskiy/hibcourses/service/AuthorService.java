package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

    AuthorEntity updateAuthor(AuthorEntity author);

    AuthorEntity getAuthor(Long id);

    List<AuthorEntity> getAllAuthors();

    void deleteAuthor(Long id);
}
