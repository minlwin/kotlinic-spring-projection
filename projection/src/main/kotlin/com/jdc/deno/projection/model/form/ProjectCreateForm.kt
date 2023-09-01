package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProjectCreateForm (
    @NotBlank(message = "Please enter project name.")
    val name:String,
    @NotNull(message = "Please set project type.")
    val type:ProjectType,
    val planId:Long,
    val merchantId:Long
)