package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.Merchant
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

data class MerchantDto(
        val id:Long,
        val name:String,
        val email:String) {

    companion object {
        fun select(query:CriteriaQuery<MerchantDto>, root:Root<Merchant>):CriteriaQuery<MerchantDto> {
            return query.multiselect(
                    root.get<Long>("id"),
                    root.get<String>("name"),
                    root.get<String>("email")
            )
        }
    }
}