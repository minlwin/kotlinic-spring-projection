package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

data class PricingPlanListDto(
    val id:Long,
    val name:String,
    val type:ProjectType
) {
    companion object {
        fun select(query:CriteriaQuery<PricingPlanListDto>, root: Root<PricingPlan>):CriteriaQuery<PricingPlanListDto> {
            return query.multiselect(
                root.get<Long>("id"),
                root.get<String>("name"),
                root.get<ProjectType>("type")
            )
        }
    }
}
