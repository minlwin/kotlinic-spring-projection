package com.jdc.deno.projection.api

import com.jdc.deno.projection.model.form.ProjectCreateForm
import com.jdc.deno.projection.model.form.ProjectSearchForm
import com.jdc.deno.projection.service.ProjectService
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("projects")
class ProjectApi(private val service:ProjectService) {

    @GetMapping
    fun search(
        form:ProjectSearchForm,
        @RequestParam(required = false, defaultValue = "0") current:Int,
        @RequestParam(required = false, defaultValue = "10") max:Int)
        = service.search(form, current, max)

    @GetMapping("export")
    fun export(form: ProjectSearchForm) = service.searchForExport(form)

    @PostMapping
    fun create(@Validated @RequestBody form:ProjectCreateForm, result:BindingResult)
        = service.create(form)

    @GetMapping("{id}")
    fun findById(@PathVariable id:Long) = service.findById(id)

    @PutMapping("{id}")
    fun update(@PathVariable id:Long, @Validated @RequestBody form:ProjectCreateForm, result:BindingResult)
        = service.update(id, form)
}
