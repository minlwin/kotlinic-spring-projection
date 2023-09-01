package com.jdc.deno.projection.model

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T, ID> :JpaRepository<T, ID>{
    fun <R>findAll(queryFun:QueryFunction<R>):List<R>

    fun count(queryFun:CountFunction):Long

    fun <R>findAll(current:Int, max:Int, countFun:CountFunction , queryFun:QueryFunction<R>): Page<R>
}

typealias QueryFunction<R> = (cb:CriteriaBuilder) -> CriteriaQuery<R>
typealias CountFunction = (cb:CriteriaBuilder) -> CriteriaQuery<Long>