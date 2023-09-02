package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.CountFunction
import com.jdc.deno.projection.model.dto.PageResponse
import com.jdc.deno.projection.model.dto.PricingPlanDto
import com.jdc.deno.projection.model.dto.PricingPlanListDto
import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.form.PricingPlanCreateForm
import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.model.repo.PricingPlanRepo
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PricingPlanService(private val repo:PricingPlanRepo) {

    fun search(form: PricingPlanSearchForm, current:Int, max:Int): PageResponse<PricingPlanListDto> {

        val countFunc:CountFunction = {
            val query = it.createQuery(Long::class.java)
            val root = query.from(PricingPlan::class.java)
            query.select(it.count(root))
            query.where(form.where(it, root))
            query
        }

        val page = repo.findAll(current, max, countFunc) {
            val query = it.createQuery(PricingPlanListDto::class.java)
            val root = query.from(PricingPlan::class.java)

            PricingPlanListDto.select(query, root)
            query.where(form.where(it, root))

            query
        }

        return  PageResponse(page)
    }

    fun searchForExport(form: PricingPlanSearchForm)
        = repo.findAll {
            val query = it.createQuery(PricingPlanListDto::class.java)
            val root = query.from(PricingPlan::class.java)

            PricingPlanListDto.select(query, root)
            query.where(form.where(it, root))

            query
        }

    fun findById(id:Long):PricingPlanDto
        = repo.findById(id).map { PricingPlanDto.from(it) }
            .orElseThrow { EntityNotFoundException("There is no entity with id $id") }

    @Transactional
    fun create(form: PricingPlanCreateForm)
        = repo.save(form.entity()).let { PricingPlanDto.from(it) }

    @Transactional
    fun update(id: Long, form: PricingPlanCreateForm): PricingPlanDto =
        repo.findById(id).map {
            it.name = form.name
            it.type = form.type
            it
        }.map { PricingPlanDto.from(it) }
            .orElseThrow { EntityNotFoundException("There is no entity with id $id") }

}