package com.elotech.viniciuspdionizio.library_api.service;

import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.loan.LoanResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.mapper.LoanMapper;
import com.elotech.viniciuspdionizio.library_api.repository.BookRepository;
import com.elotech.viniciuspdionizio.library_api.repository.LoanRepository;
import com.elotech.viniciuspdionizio.library_api.repository.UserRepository;
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

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoanService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public Page<LoanResponseDTO> getAll(@Nullable Integer userId, @Nullable Integer bookId,
                                        @Nonnull Boolean status, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().and(Sort.by("id")));
        return this.loanRepository.findAll(userId, bookId, status, pageable).map(loanMapper::toDTO);
    }

    @Transactional
    public LoanResponseDTO register(@Nonnull LoanRequestDTO requestData) {
        this.checkIfExists(requestData.userId(), requestData.bookId());
        var entity = this.loanMapper.toEntity(requestData);
        this.loanRepository.registerLoan(entity);
        return this.loanMapper.toDTO(entity);
    }

    @Transactional
    public LoanResponseDTO update(@Nonnull Integer id, @Nullable Boolean status, @Nullable LocalDate returnDate) {
        if (Stream.of(status, returnDate).allMatch(Objects::isNull))
            throw new IllegalArgumentException("At least one param should be not null [%s, %s]".formatted("status", "returnDate"));
        var entity = this.loanRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Empréstimo"));
        if (status != null) entity.setStatus(status);
        if (returnDate != null) entity.setReturnDate(returnDate);
        return this.loanMapper.toDTO(entity);
    }

    private void checkIfExists(Integer userId, Integer bookId) throws ObjectNotFoundException {
        if (userId != null && !this.userRepository.existsById(userId))
            throw new ObjectNotFoundException(userId, "Usuário");
        if (bookId != null && !this.bookRepository.existsById(bookId))
            throw new ObjectNotFoundException(bookId, "Livro");
    }

}
