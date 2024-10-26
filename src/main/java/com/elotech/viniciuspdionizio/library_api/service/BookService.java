package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.book.BookResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.BookMapper;
import com.elotech.viniciuspdionizio.library_api.repository.BookRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponseDTO getById(@Nonnull Integer id) {
        return this.bookRepository.findById(id)
                .map(this.bookMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(id, "book"));
    }

    public Page<BookResponseDTO> getAll(@Nullable String filter, @Nonnull Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().and(Sort.by("id")));
        return this.bookRepository.findAll(filter, pageable).map(this.bookMapper::toDTO);
    }

    @Transactional
    public BookResponseDTO register(@Nonnull BookRequestDTO requestData) {
        var entity = this.bookMapper.toEntity(requestData);
        this.bookRepository.save(entity);
        return this.bookMapper.toDTO(entity);
    }

    @Transactional
    public BookResponseDTO update(@Nonnull Integer id, @Nonnull BookRequestDTO requestData) {
        var entity = this.bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "book"));
        this.bookMapper.update(requestData, entity);
        this.bookRepository.save(entity);
        return this.bookMapper.toDTO(entity);
    }

    @Transactional
    public void delete(@Nonnull Integer id) {
        if (!this.bookRepository.existsById(id)) throw new ObjectNotFoundException(id, "book");
        this.bookRepository.deleteById(id);
    }


}
