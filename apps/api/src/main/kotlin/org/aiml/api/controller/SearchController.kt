package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.ok
import org.aiml.api.dto.project.ProjectPageResponse
import org.aiml.api.dto.user.UserCoreResponse
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.aiml.user.domain.port.inbound.UserCoreCommandService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val userCoreCommandService: UserCoreCommandService,
    private val projectQueryService: ProjectQueryService,
){
  @GetMapping("/user")
  fun searchUsers(
    @RequestParam("username", defaultValue = "") username: String,
  ): ResponseEntity<ApiResponse<List<UserCoreResponse>>> {
    val users = userCoreCommandService.search(username)
    return ok(users.map { UserCoreResponse.from(it) })
  }

  @GetMapping("/project")
  fun searchProjects(
    @RequestParam("q", defaultValue = "") query: String,
    @RequestParam("page", defaultValue = "0") page: Int,
    @RequestParam("size", defaultValue = "20") size: Int,
  ): ResponseEntity<ApiResponse<ProjectPageResponse>> {
    val pInfosPage = projectQueryService.searchByQuery(query, PageRequest.of(page, size)) // searchByQueryAndPageable
    return ok(ProjectPageResponse.from(pInfosPage))
  }
}
