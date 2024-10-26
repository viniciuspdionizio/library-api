package com.elotech.viniciuspdionizio.library_api.repository;

import com.elotech.viniciuspdionizio.library_api.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("""
            SELECT u FROM UserEntity u
            WHERE
            TRIM(u.name) ILIKE '%' || TRIM(COALESCE(:filter, '')) || '%'
            """)
    Page<UserEntity> findAll(String filter, Pageable pageable);

}
