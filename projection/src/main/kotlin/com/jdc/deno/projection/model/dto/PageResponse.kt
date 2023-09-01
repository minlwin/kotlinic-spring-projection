package com.jdc.deno.projection.model.dto

data class PageResponse<T>(
    val contents:List<T>,
    val current:Int,
    val max:Int,
    val count:Int
) {
    val pages:Int
        get() = if(count % max > 0) count / max + 1 else count / max
}
