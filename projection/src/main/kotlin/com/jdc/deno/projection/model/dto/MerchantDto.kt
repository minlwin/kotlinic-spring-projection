package com.jdc.deno.projection.model.dto

import com.jdc.deno.projection.model.entity.Merchant

data class MerchantDto(
    val id:Long,
    val name:String,
    val phone:String,
    val email:String
) {
    companion object {
        fun from(entity:Merchant) = MerchantDto(id = entity.id, name = entity.name, phone = entity.phone, email = entity.email)
    }
}
