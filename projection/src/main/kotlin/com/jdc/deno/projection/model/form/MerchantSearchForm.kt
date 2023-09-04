package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.Merchant
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.util.StringUtils
import java.time.LocalDate

data class MerchantSearchForm(
        val name:String?,
        @DateTimeFormat(pattern = "yyyyMMdd")
        val from:LocalDate?,
        val phone:String?,
        val email:String?
) {
    fun where(cb:CriteriaBuilder, root: Root<Merchant>): Predicate {

        var predicate = cb.isFalse(root.get("deleted"))

        if(null != name && StringUtils.hasLength(name)) {
            predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "${name.lowercase()}%"))
        }

        if(null != from) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("registerAt"), from))
        }

        if(null != phone && StringUtils.hasLength(phone)) {
            predicate = cb.and(predicate, cb.like(root.get("phone"), "${phone.lowercase()}%"))
        }

        if(null != email && StringUtils.hasLength(email)) {
            predicate = cb.and(predicate, cb.like(root.get("email"), "${email.lowercase()}%"))
        }

        return predicate
    }
}