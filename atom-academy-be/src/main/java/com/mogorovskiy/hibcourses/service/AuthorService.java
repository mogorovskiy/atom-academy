package com.mogorovskiy.hibcourses.service;

import com.mogorovskiy.hibcourses.api.request.create.AuthorCreateAndUpdateRequest;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorCreateAndUpdateRequest author);

    AuthorEntity updateAuthor(Long id, AuthorCreateAndUpdateRequest author);

    AuthorEntity patchAuthor(Long id, AuthorCreateAndUpdateRequest author);

    AuthorEntity getAuthor(Long id);

    List<AuthorEntity> getAllAuthors();

    void deleteAuthor(Long id);
}
