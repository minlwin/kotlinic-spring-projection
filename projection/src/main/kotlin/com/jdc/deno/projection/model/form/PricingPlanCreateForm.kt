package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PricingPlanCreateForm (
    @NotBlank(message = "Please enter plan name.")
    val name:String,
    @NotNull(message = "Please enter type of project.")
    val type:ProjectType
)