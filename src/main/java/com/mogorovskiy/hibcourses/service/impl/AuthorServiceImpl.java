package com.mogorovskiy.hibcourses.service.impl;

import com.mogorovskiy.hibcourses.api.AuthorCreateRequest;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import com.mogorovskiy.hibcourses.repository.AuthorRepository;
import com.mogorovskiy.hibcourses.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public AuthorEntity createAuthor(AuthorCreateRequest authorCreateRequest) {
        log.info("Creating author in DB: {}", authorCreateRequest.name());
        AuthorEntity authorEntity = AuthorEntity.builder()
                .name(authorCreateRequest.name())
                .email(authorCreateRequest.email())
                .build();

        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity updateAuthor(AuthorCreateRequest author) {
        return null;
    }

    @Override
    public AuthorEntity getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteAuthor(Long id) {
        log.info("Deleting author by id: {}", id);
        authorRepository.deleteById(id);
    }
}
