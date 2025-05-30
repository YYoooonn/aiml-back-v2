package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.ok
import org.aiml.api.dto.user.UserResponse
import org.aiml.api.common.response.PageResponse
import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.aiml.user.application.UserServiceFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(
  private val projectQueryService: ProjectQueryService,
  private val userServiceFacade: UserServiceFacade,
){

  @GetMapping("/user")
  fun searchUsers(
    @RequestParam("username", defaultValue = "") username: String,
    @RequestParam("page", defaultValue = "0") page: Int,
    @RequestParam("size", defaultValue = "5") size: Int,
  ): ResponseEntity<ApiResponse<PageResponse<UserResponse>>> {
    val uInfosPage = userServiceFacade.searchByUsername(username, PageRequest.of(page, size))
    val userResponsePage: Page<UserResponse> = uInfosPage.map { userDto -> UserResponse.from(userDto) }
    return ok(PageResponse.from(userResponsePage))
  }

  @GetMapping("/project")
  fun searchProjects(
    @RequestParam("q", defaultValue = "") query: String,
    @RequestParam("page", defaultValue = "0") page: Int,
    @RequestParam("size", defaultValue = "20") size: Int,
  ): ResponseEntity<ApiResponse<PageResponse<ProjectDTO>>> {
    val pInfosPage = projectQueryService.searchByQuery(query, PageRequest.of(page, size)) // searchByQueryAndPageable
    return ok(PageResponse.from(pInfosPage))
  }
}
