package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.consts.ProjectType

data class ProjectCreateForm (
    val name:String,
    val type:ProjectType
)