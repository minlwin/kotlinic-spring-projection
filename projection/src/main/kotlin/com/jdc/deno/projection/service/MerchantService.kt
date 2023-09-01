package com.jdc.deno.projection.service

import com.jdc.deno.projection.model.dto.MerchantDto
import com.jdc.deno.projection.model.entity.Merchant
import com.jdc.deno.projection.model.form.MerchantSearchForm
import com.jdc.deno.projection.model.repo.MerchantRepo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MerchantService(private val repo:MerchantRepo) {

    @Transactional(readOnly = true)
    fun search(form: MerchantSearchForm): List<MerchantDto> {
        return repo.findAll {
            var query = it.createQuery(MerchantDto::class.java)
            val root = query.from(Merchant::class.java)
            query = MerchantDto.select(query, root)
            query.where(form.where(it, root))
            query
        }
    }

}