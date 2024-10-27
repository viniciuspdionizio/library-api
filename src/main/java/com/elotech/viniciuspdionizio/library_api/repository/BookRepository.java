package com.elotech.viniciuspdionizio.library_api.repository;

import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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


    @Query(value = """
            WITH books_already_loan AS (
                SELECT book_id, category
                FROM book
                INNER JOIN loan USING (book_id)
                WHERE user_id = :userId
            )
            SELECT *
            FROM book b
            WHERE NOT EXISTS (
                SELECT 1
                FROM books_already_loan bal
                WHERE bal.book_id = b.book_id
            )
            AND b.category IN (SELECT category FROM books_already_loan);
            """, nativeQuery = true)
    List<BookEntity> findAllRecommendations(Integer userId);

}
