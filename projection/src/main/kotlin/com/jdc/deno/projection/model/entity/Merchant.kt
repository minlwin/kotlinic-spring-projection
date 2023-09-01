package com.jdc.deno.projection.model.entity

import jakarta.persistence.*

@Entity
@SequenceGenerator(name = "seq_merchant", allocationSize = 1)
data class Merchant (
    @Id
    @GeneratedValue(generator = "seq_merchant")
    var id:Long= 0L,
    @Column(nullable = false)
    var name:String = "",
    @Column(nullable = false)
    var phone:String = "",
    @Column(nullable = false)
    var email:String = "",
    var deleted:Boolean = false
)