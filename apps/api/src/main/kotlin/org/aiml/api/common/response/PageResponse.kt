package org.aiml.api.common.response

import org.springframework.data.domain.Page

data class PageResponse<T>(
  val content: List<T>,
  val pageInfo: PageInfo
) {
  companion object {
    fun <E> from(page: Page<E>): PageResponse<E> {
      val content = page.content
      val pageInfo = PageInfo(
        page = page.number,
        size = page.size,
        hasNext = page.hasNext(),
        hasPrevious = page.number > 0 && page.totalElements > ((page.number - 1).toLong() * page.size),
        sort = page.sort.toString()
      )
      return PageResponse(
        content = content,
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
