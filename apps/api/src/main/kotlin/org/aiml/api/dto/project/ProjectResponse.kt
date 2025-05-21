package org.aiml.api.dto.project

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project_user.application.dto.ProjectUserNameDTO
import org.springframework.data.domain.Page
import java.time.LocalDateTime
import java.util.*

data class ProjectBaseResponse(
  val id: UUID,
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean? = true,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
) {
  companion object {
    fun from(dto: ProjectDTO): ProjectBaseResponse = ProjectBaseResponse(
      id = dto.id!!,
      title = dto.title,
      description = dto.description,
      subtitle = dto.subtitle,
      isPublic = dto.isPublic,
      createdAt = dto.createdAt,
      updatedAt = dto.updatedAt,
    )
  }
}

data class ProjectUserResponse(
  val projectId: UUID,
  val username: String,
  val role: String,
) {
  companion object {
    fun from(dto: ProjectUserNameDTO): ProjectUserResponse = ProjectUserResponse(
      projectId = dto.projectId,
      username = dto.username,
      role = dto.role.toString(),
    )
  }
}

data class ProjectPageResponse(
  val projects: List<ProjectBaseResponse>,
  val pageInfo: PageInfo
) {
  companion object {
    fun from(page: Page<ProjectDTO>): ProjectPageResponse {
      val projects = page.content.map { ProjectBaseResponse.from(it) }
      val pageInfo = PageInfo(
        page = page.number,
        size = page.size,
        hasNext = page.hasNext(),
        hasPrevious = page.hasPrevious(),
        sort = page.sort.toString()
      )
      return ProjectPageResponse(
          projects = projects,
          pageInfo = pageInfo
      )
    }
  }
}

data class PageInfo (
  val page: Int,                // 현재 페이지 번호 (0부터 시작)
  val size: Int,                // 페이지 요청 시 크기
  val hasNext: Boolean,         // 다음 페이지 존재 여부
  val hasPrevious: Boolean,     // 이전 페이지 존재 여부
  val sort: String              // 정렬 기준
)
