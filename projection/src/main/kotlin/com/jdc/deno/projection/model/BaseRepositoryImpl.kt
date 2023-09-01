package com.jdc.deno.projection.model

import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

class BaseRepositoryImpl<T, ID>(entityInformation: JpaEntityInformation<T, ID>, private val em:EntityManager)
    : SimpleJpaRepository<T, ID>(entityInformation, em), BaseRepository<T, ID> {

    override fun <R> findAll(queryFun: (cb: CriteriaBuilder) -> CriteriaQuery<R>): List<R> {
        return em.createQuery(queryFun(em.criteriaBuilder)).resultList
    }

    override fun count(queryFun: (cb: CriteriaBuilder) -> CriteriaQuery<Long>): Long {
        return em.createQuery(queryFun(em.criteriaBuilder)).singleResult
    }

    override fun <R> findAll(current: Int, max: Int, countFun: CountFunction, queryFun: QueryFunction<R>): Page<R> {
        val pageable = PageRequest.of(current, max)
        val count = em.createQuery(countFun(em.criteriaBuilder)).singleResult

        val query = em.createQuery(queryFun(em.criteriaBuilder))
        query.setFirstResult(current * max)
        query.setMaxResults(max)

        return PageImpl<R>(query.resultList, pageable, count)
    }
}