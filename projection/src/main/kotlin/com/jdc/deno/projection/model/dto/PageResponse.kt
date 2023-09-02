package com.jdc.deno.projection.model.dto

import org.springframework.data.domain.Page

data class PageResponse<T>(
    val contents:List<T>,
    val current:Int,
    val max:Int,
    val count:Int
) {
    val pages:Int
        get() = if(count % max > 0) count / max + 1 else count / max

    constructor(page:Page<T>):this(page.content, page.number, page.size, page.count())
}
