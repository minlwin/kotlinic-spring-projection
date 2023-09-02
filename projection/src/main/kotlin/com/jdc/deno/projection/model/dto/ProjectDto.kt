package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.Project
import com.jdc.deno.projection.model.entity.consts.ProjectType

data class ProjectDto(
    val id:Long,
    val name:String,
    val type:ProjectType,
    val owner:MerchantDto,
    val plan:PricingPlanDto,
    val deleted:Boolean
) {
    companion object {
        fun from(entity:Project):ProjectDto {
            return ProjectDto(entity.id, entity.name, entity.type, MerchantDto.from(entity.merchant!!), PricingPlanDto.from(entity.plan!!), entity.deleted)
        }
    }
}
