package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.CountFunction
import com.jdc.deno.projection.model.dto.PageResponse
import com.jdc.deno.projection.model.dto.ProjectDto
import com.jdc.deno.projection.model.dto.ProjectListDto
import com.jdc.deno.projection.model.entity.Project
import com.jdc.deno.projection.model.form.ProjectCreateForm
import com.jdc.deno.projection.model.form.ProjectSearchForm
import com.jdc.deno.projection.model.repo.MerchantRepo
import com.jdc.deno.projection.model.repo.PricingPlanRepo
import com.jdc.deno.projection.model.repo.ProjectRepo
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProjectService(
    private val repo:ProjectRepo,
    private val merchantRepo: MerchantRepo,
    private val pricingPlanRepo: PricingPlanRepo) {

    fun search(form: ProjectSearchForm, current: Int, max: Int) : PageResponse<ProjectListDto> {

        val countFunc:CountFunction = {
            val query = it.createQuery(Long::class.java)
            val root = query.from(Project::class.java)
            query.select(it.count(root))
            query.where(form.where(it, root))
            query
        }

        val page = repo.findAll(current, max, countFunc) {
            val query = it.createQuery(ProjectListDto::class.java)
            val root = query.from(Project::class.java)
            ProjectListDto.select(query, root)
            query.where(form.where(it, root))
            query
        }

        return PageResponse(page)
    }

    fun searchForExport(form: ProjectSearchForm)
        = repo.findAll {
            val query = it.createQuery(ProjectListDto::class.java)
            val root = query.from(Project::class.java)
            ProjectListDto.select(query, root)
            query.where(form.where(it, root))
            query
        }

    @Transactional
    fun create(form: ProjectCreateForm): ProjectDto {
        val merchant = merchantRepo.findById(form.merchantId)
            .orElseThrow { EntityNotFoundException("There is no merchant with id: ${form.merchantId}") }

        val plan = pricingPlanRepo.findById(form.planId)
            .orElseThrow { EntityNotFoundException("There is no pricing plan with id: ${form.planId}") }

        return repo.save(form.entity(merchant, plan)).let { ProjectDto.from(it) }
    }

    fun findById(id: Long): ProjectDto {
        return repo.findById(id).map { ProjectDto.from(it) }
            .orElseThrow { EntityNotFoundException("There is no project with id: $id") }
    }

    @Transactional
    fun update(id: Long, form: ProjectCreateForm): ProjectDto {
        return repo.findById(id)
            .map {
                it.name = form.name
                it.type = form.type
                it.plan = pricingPlanRepo.getReferenceById(form.planId)
                it.merchant = merchantRepo.getReferenceById(form.merchantId)
                it }
            .map { ProjectDto.from(it) }
            .orElseThrow { EntityNotFoundException("There is no project with id: $id") }
    }


}