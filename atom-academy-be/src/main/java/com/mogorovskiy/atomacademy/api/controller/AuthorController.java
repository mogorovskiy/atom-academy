package com.mogorovskiy.atomacademy.api.controller;

import com.mogorovskiy.atomacademy.api.request.create.AuthorCreateAndUpdateRequest;
import com.mogorovskiy.atomacademy.domain.dto.AuthorDto;
import com.mogorovskiy.atomacademy.domain.entities.AuthorEntity;
import com.mogorovskiy.atomacademy.domain.mapper.AuthorMapper;
import com.mogorovskiy.atomacademy.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorCreateAndUpdateRequest createRequest) {
        log.info("Creating author: {}", createRequest);

        AuthorEntity entity = authorService.createAuthor(createRequest);
        AuthorDto authorDto = authorMapper.toAuthorDto(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long id) {
        log.info("Getting author by id: {}", id);

        AuthorEntity entity = authorService.getAuthor(id);
        AuthorDto authorDto = authorMapper.toAuthorDto(entity);

        return ResponseEntity.status(HttpStatus.OK).body(authorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorCreateAndUpdateRequest updateRequest
    ) {
        log.info("Updating author id {}: {}", id, updateRequest);
        AuthorEntity entity = authorService.updateAuthor(id, updateRequest);
        return ResponseEntity.ok(authorMapper.toAuthorDto(entity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> patchAuthor(
            @PathVariable Long id,
            @RequestBody AuthorCreateAndUpdateRequest updateRequest) {
        log.info("Patching author id {}: {}", id, updateRequest);
        AuthorEntity entity = authorService.patchAuthor(id, updateRequest);
        return ResponseEntity.ok(authorMapper.toAuthorDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long id) {
        log.info("Deleting author by id: {}", id);

        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
