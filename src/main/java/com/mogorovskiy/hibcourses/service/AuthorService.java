package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.api.AuthorCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorCreateRequest author);

    AuthorEntity updateAuthor(AuthorCreateRequest author);

    AuthorEntity getAuthor(Long id);

    List<AuthorEntity> getAllAuthors();

    void deleteAuthor(Long id);
}
