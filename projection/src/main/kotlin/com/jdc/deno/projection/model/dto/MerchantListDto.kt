package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.Merchant
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import java.time.LocalDate

data class MerchantListDto(
        val id:Long,
        val name:String,
        val registerAt:LocalDate,
        val email:String) {

    companion object {
        fun select(query:CriteriaQuery<MerchantListDto>, root:Root<Merchant>):CriteriaQuery<MerchantListDto> {
            return query.multiselect(
                    root.get<Long>("id"),
                    root.get<String>("name"),
                    root.get<LocalDate>("registerAt"),
                    root.get<String>("email")
            )
        }
    }
}