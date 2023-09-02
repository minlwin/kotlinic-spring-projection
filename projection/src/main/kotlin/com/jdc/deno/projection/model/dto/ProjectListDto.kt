package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.Merchant
import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.entity.Project
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

data class ProjectListDto(
    val id:Long,
    val name:String,
    val merchantId:Long,
    val merchantName:String,
    val merchantEmail:String,
    val merchantPhone:String,
    val pricingId:Long,
    val pricingName:String
) {
    companion object {
        fun select(cq:CriteriaQuery<ProjectListDto>, root: Root<Project>): CriteriaQuery<ProjectListDto> =
            cq.multiselect(
                root.get<Long>("id"),
                root.get<String>("name"),
                root.get<Merchant>("merchant").get<Long>("id"),
                root.get<Merchant>("merchant").get<String>("name"),
                root.get<Merchant>("merchant").get<String>("email"),
                root.get<Merchant>("merchant").get<String>("phone"),
                root.get<PricingPlan>("plan").get<Long>("id"),
                root.get<PricingPlan>("plan").get<String>("name")
            )
    }
}
