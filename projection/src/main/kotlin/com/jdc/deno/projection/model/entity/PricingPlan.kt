package com.jdc.deno.projection.model.entity

import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.persistence.*

@Entity
@SequenceGenerator(name = "seq_pricing_plan", allocationSize = 1)
data class PricingPlan(
    @Id
    @GeneratedValue(generator = "seq_pricing_plan")
    var id:Long = 0,
    @Column(nullable = false)
    var name:String = "",
    @Column(nullable = false)
    var type:ProjectType = ProjectType.DigitalContent,
    var deleted:Boolean = false
)