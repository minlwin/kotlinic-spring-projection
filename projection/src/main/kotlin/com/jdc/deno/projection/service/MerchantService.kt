package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.dto.MerchantDto
import com.jdc.deno.projection.model.dto.MerchantListDto
import com.jdc.deno.projection.model.entity.Merchant
import com.jdc.deno.projection.model.form.MerchantCreateForm
import com.jdc.deno.projection.model.form.MerchantSearchForm
import com.jdc.deno.projection.model.repo.MerchantRepo
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MerchantService(private val repo:MerchantRepo) {

    @Transactional(readOnly = true)
    fun search(form: MerchantSearchForm): List<MerchantListDto> {
        return repo.findAll {
            val query = it.createQuery(MerchantListDto::class.java)
            val root = query.from(Merchant::class.java)
            MerchantListDto.select(query, root)
            query.where(form.where(it, root))
            query
        }
    }

    @Transactional
    fun create(form: MerchantCreateForm):MerchantDto {
        return repo.save(form.entity()).let { MerchantDto.from(it) }
    }

    fun findById(id: Long): MerchantDto {
        return repo.findById(id).map { MerchantDto.from(it) }
            .orElseThrow { EntityNotFoundException("There is no entity with id $id") }
    }

    @Transactional
    fun update(id: Long, form: MerchantCreateForm): MerchantDto {
        return repo.findById(id).map {
                it.name = form.name
                it.phone = form.phone
                it.email = form.email
                it
            }.map { MerchantDto.from(it) }
                .orElseThrow { EntityNotFoundException("There is no entity with id $id") }
    }

}