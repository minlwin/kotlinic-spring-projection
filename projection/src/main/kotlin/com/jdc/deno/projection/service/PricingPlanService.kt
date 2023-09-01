package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.CountFunction
import com.jdc.deno.projection.model.dto.PageResponse
import com.jdc.deno.projection.model.dto.PricingPlanListDto
import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.form.PricingPlanSearchForm
import com.jdc.deno.projection.model.repo.PricingPlanRepo
import org.springframework.stereotype.Service

@Service
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

        return page.let {
            PageResponse(it.content, current, max, it.count())
        }
    }


}