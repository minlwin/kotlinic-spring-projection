package com.jdc.deno.projection.model.entity

import com.jdc.deno.projection.model.entity.consts.ProjectType
import jakarta.persistence.*

@Entity
@SequenceGenerator(name = "seq_project")
data class Project (
    @Id
    @GeneratedValue(generator = "seq_project")
    var id:Long = 0,
    @Column(nullable = false)
    var name:String = "",
    @Column(nullable = false)
    var type:ProjectType = ProjectType.DigitalContent,
    @ManyToOne(optional = false)
    var plan:PricingPlan? = null,
    @ManyToOne(optional = false)
    var merchant:Merchant? = null,
    var deleted:Boolean = false
)