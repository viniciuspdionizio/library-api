package com.elotech.viniciuspdionizio.library_api.repository;

import com.elotech.viniciuspdionizio.library_api.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("""
            SELECT b FROM BookEntity b
            WHERE TRIM(b.title) ILIKE '%' || TRIM(COALESCE(:filter, '')) || '%'
            """)
    Page<BookEntity> findAll(String filter, Pageable pageable);

}
