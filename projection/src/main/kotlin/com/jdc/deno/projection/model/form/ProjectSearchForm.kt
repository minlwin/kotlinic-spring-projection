package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.Merchant
import com.jdc.deno.projection.model.entity.Project
import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.util.StringUtils

data class ProjectSearchForm (
    val merchantId:Long?,
    val name:String?,
    val type:ProjectType?
) {

    fun where(cb:CriteriaBuilder, root:Root<Project>):Predicate {
        var predicate = cb.isFalse(root.get("deleted"))

        if(null != merchantId && merchantId > 0) {
            predicate = cb.and(predicate, cb.equal(root.get<Merchant>("merchant").get<Long>("id"), merchantId))
        }

        if (null != type) {
            predicate = cb.and(predicate, cb.equal(root.get<ProjectType>("type"), type))
        }

        if(null != name && StringUtils.hasLength(name)) {
            predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "${name.lowercase()}%"))
        }

        return predicate
    }
}