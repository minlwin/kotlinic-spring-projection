package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.entity.consts.ProjectType

data class PricingPlanDto(
    val id:Long,
    val name:String,
    val type:ProjectType
) {
    companion object {
        fun from(entity:PricingPlan)
            = PricingPlanDto(entity.id, entity.name, entity.type)
    }
}