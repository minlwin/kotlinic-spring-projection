package com.jdc.deno.projection

import com.jdc.deno.projection.model.BaseRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl::class)
class ProjectionApplication

fun main(args: Array<String>) {
	runApplication<ProjectionApplication>(*args)
}
