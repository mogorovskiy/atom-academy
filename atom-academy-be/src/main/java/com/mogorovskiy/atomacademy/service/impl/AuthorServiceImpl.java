package com.mogorovskiy.atomacademy.service.impl;

import com.mogorovskiy.atomacademy.api.request.create.AuthorCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.entities.AuthorEntity;
import com.mogorovskiy.atomacademy.repository.AuthorRepository;
import com.mogorovskiy.atomacademy.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
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
    public AuthorEntity createAuthor(AuthorCreateAndUpdateRequest authorCreateRequest) {
        log.info("Creating author in DB: {}", authorCreateRequest.name());
        AuthorEntity authorEntity = AuthorEntity.builder()
                .name(authorCreateRequest.name())
                .email(authorCreateRequest.email())
                .build();

        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity updateAuthor(Long id, AuthorCreateAndUpdateRequest author) {
        AuthorEntity entity = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        entity.setName(author.name());
        entity.setEmail(author.email());

        return authorRepository.save(entity);
    }

    @Override
    public AuthorEntity patchAuthor(Long id, AuthorCreateAndUpdateRequest createRequest) {
        AuthorEntity entity = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        boolean changed = false;
        if (createRequest.name() != null) {
            changed |= !createRequest.name().equals(entity.getName());
            entity.setName(createRequest.name());
        }
        if (createRequest.email() != null) {
            changed |= !createRequest.email().equals(entity.getEmail());
            entity.setEmail(createRequest.email());
        }

        if (changed) {
            return authorRepository.save(entity);
        }

        return entity;
    }

    @Override
    public AuthorEntity getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
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
