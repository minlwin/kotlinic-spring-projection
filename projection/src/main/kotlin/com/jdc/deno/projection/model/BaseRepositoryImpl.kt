package com.jdc.deno.projection.model

import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

class BaseRepositoryImpl<T, ID>(entityInformation: JpaEntityInformation<T, ID>, var em:EntityManager)
    : SimpleJpaRepository<T, ID>(entityInformation, em), BaseRepository<T, ID> {

    override fun <R> findAll(query: (cb: CriteriaBuilder) -> CriteriaQuery<R>): List<R> {
        return em.createQuery(query(em.criteriaBuilder)).resultList
    }
}