package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.ok
import org.aiml.api.dto.project.ProjectBaseResponse
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/archive")
class ArchiveController(
  private val projectQueryService: ProjectQueryService,
) {

  @GetMapping("/search")
  fun searchProjects(
    @RequestParam("query", required = true) query: String,
    @RequestParam("pageNum", defaultValue = "0") page: Int,
    @RequestParam("pageSize", defaultValue = "10") size: Int,
  ): ResponseEntity<ApiResponse<Page<ProjectBaseResponse>>> {
    val pInfosPage = projectQueryService.searchByQuery(query, PageRequest.of(page, size))
    return ok(pInfosPage.map { ProjectBaseResponse.from(it) })
  }
}
