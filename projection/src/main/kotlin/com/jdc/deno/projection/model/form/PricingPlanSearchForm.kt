package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.PricingPlan
import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.util.StringUtils

data class PricingPlanSearchForm(
    val type:ProjectType?,
    val name:String?
) {
    fun where(cb:CriteriaBuilder, root: Root<PricingPlan>):Predicate {
        var predicate = cb.isFalse(root.get("deleted"))

        if(null != type) {
            predicate = cb.and(predicate, cb.equal(root.get<ProjectType>("type"), type))
        }

        if(null != name && StringUtils.hasLength(name)) {
            predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "${name.lowercase()}%"))
        }

        return predicate
    }
}
