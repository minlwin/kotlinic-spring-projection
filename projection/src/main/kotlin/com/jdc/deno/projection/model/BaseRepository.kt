package com.jdc.deno.projection.model

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T, ID> :JpaRepository<T, ID>{
    fun <R>findAll(query:(cb:CriteriaBuilder) -> CriteriaQuery<R>):List<R>
}