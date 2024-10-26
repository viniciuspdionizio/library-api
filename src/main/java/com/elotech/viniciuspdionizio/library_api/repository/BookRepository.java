package com.elotech.viniciuspdionizio.library_api.repository;

import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import com.elotech.viniciuspdionizio.library_api.model.enums.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("""
            SELECT b FROM BookEntity b
            WHERE
            (:status IS NULL OR
             CASE WHEN :status = TRUE THEN
             NOT EXISTS (
                SELECT TRUE FROM LoanEntity l
                WHERE l.book.id = b.id
            )
            ELSE EXISTS (
                SELECT TRUE FROM LoanEntity l
                WHERE l.book.id = b.id
            ) END
            )
            AND TRIM(b.title) ILIKE '%' || TRIM(COALESCE(:filter, '')) || '%'
            """)
    Page<BookEntity> findAll(String filter, Boolean status, Pageable pageable);


    @Query("""
            SELECT b FROM BookEntity b
            WHERE b.category IN (:categories)
            """)
    Page<BookEntity> findAllRecommendations(Collection<BookCategory> categories, Pageable pageable);

    @Query("""
            SELECT DISTINCT b.category FROM BookEntity b
            JOIN b.loans l
            WHERE l.user.id = :userId
            AND (:status IS NULL OR
             CASE WHEN :status = TRUE THEN
             NOT EXISTS (
                SELECT TRUE FROM LoanEntity sub
                WHERE sub.book.id = b.id
            )
            ELSE EXISTS (
                SELECT TRUE FROM LoanEntity sub
                WHERE sub.book.id = b.id
            ) END
            )
            """)
    List<BookCategory> findCategoriesByUser(Integer userId, Boolean status);

}
