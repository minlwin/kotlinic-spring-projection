package com.jdc.deno.projection.model.form

import com.jdc.deno.projection.model.entity.Merchant
import jakarta.validation.constraints.NotBlank

data class MerchantCreateForm (
    @NotBlank(message = "Please enter merchant name.")
    val name:String,
    @NotBlank(message = "Please enter merchant phone.")
    val phone:String,
    @NotBlank(message = "Please enter merchant email.")
    val email:String
) {
    fun entity():Merchant {
        return Merchant(name = name, phone = phone, email = email)
    }
}