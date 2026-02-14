package com.mogorovskiy.hibcourses.api.controller;

import com.mogorovskiy.hibcourses.api.AuthorCreateRequest;
import com.mogorovskiy.hibcourses.domain.dto.AuthorDto;
import com.mogorovskiy.hibcourses.domain.entities.AuthorEntity;
import com.mogorovskiy.hibcourses.domain.mapper.AuthorMapper;
import com.mogorovskiy.hibcourses.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorCreateRequest createRequest) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long id) {
        log.info("Deleting product by id: {}", id);

        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
