package com.elotech.viniciuspdionizio.library_api.repository;

import com.elotech.viniciuspdionizio.library_api.model.entity.LoanEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public class LoanRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<LoanEntity> findById(Integer id) {
        return Optional.ofNullable(em.find(LoanEntity.class, id));
    }

    public Page<LoanEntity> findAll(Integer userId, Integer bookId, Boolean status, Pageable pageable) {
        var loans = em.createQuery("""
                        SELECT l FROM LoanEntity l
                        WHERE l.status = :status
                        AND (:userId IS NULL OR l.user.id = :userId)
                        AND (:bookId IS NULL OR l.book.id = :bookId)
                        """, LoanEntity.class)
                .setParameter("userId", userId)
                .setParameter("bookId", bookId)
                .setParameter("status", status)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        var totalElements = em.createQuery("""
                        SELECT COUNT(l) FROM LoanEntity l
                        WHERE l.status = :status
                        AND (:userId IS NULL OR l.user.id = :userId) 
                        AND (:bookId IS NULL OR l.book.id = :bookId) 
                        """, Long.class)
                .setParameter("userId", userId)
                .setParameter("bookId", bookId)
                .setParameter("status", status)
                .getSingleResult();

        return new PageImpl<>(loans, pageable, totalElements);
    }

    public Boolean existsActiveLoan(Integer userId, Integer bookId) {
        return em.createQuery("""
                        SELECT EXISTS (
                            SELECT l FROM LoanEntity l
                            WHERE l.status = true
                            AND (:userId IS NULL OR l.user.id = :userId)
                            AND (:bookId IS NULL OR l.book.id = :bookId)
                        )
                        """, Boolean.class)
                .setParameter("userId", userId)
                .setParameter("bookId", bookId)
                .getSingleResult();
    }

    public void registerLoan(LoanEntity loan) {
        this.checkLoan(loan.getBook().getId());
        em.persist(loan);
    }

    private void checkLoan(@Nonnull Integer bookId, Integer... idsToIgnore) {
        boolean exists = em.createQuery("SELECT EXISTS (SELECT TRUE FROM LoanEntity WHERE book.id = :bookId AND status = TRUE AND id NOT IN (:idsToIgnore))", Boolean.class)
                .setParameter("bookId", bookId)
                .setParameter("idsToIgnore", Arrays.asList(idsToIgnore)).getSingleResult();
        if (exists) throw new IllegalArgumentException(String.format("Livro #%d já está emprestado", bookId));
    }

}
