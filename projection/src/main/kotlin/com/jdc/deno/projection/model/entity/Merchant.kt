package com.jdc.deno.projection.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@SequenceGenerator(name = "seq_merchant", allocationSize = 1)
data class Merchant (
    @Id
    @GeneratedValue(generator = "seq_merchant")
    var id:Long = 0L,
    @Column(nullable = false)
    var name:String = "",
    @Column(nullable = false)
    var phone:String = "",
    @Column(nullable = false)
    var email:String = "",
    @Column(columnDefinition = "date default CURRENT_TIMESTAMP()")
    var registerAt:LocalDate = LocalDate.now(),
    var deleted:Boolean = false
)