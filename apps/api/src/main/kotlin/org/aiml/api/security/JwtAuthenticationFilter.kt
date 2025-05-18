package org.aiml.api.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.http.*
import jakarta.servlet.*
import org.aiml.api.common.response.tokenError
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationFilter(
  private val jwtTokenProvider: JwtTokenProvider,
  private val userDetailsService: UserDetailsService,
  private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    try {
      // Authorization 헤더에서 토큰 추출
      val token = resolveToken(request)

      if (token != null && jwtTokenProvider.validateAccessToken(token)) {
        val username = jwtTokenProvider.getUsernameFromToken(token)
        val userDetails = userDetailsService.loadUserByUsername(username)

        // 인증 객체 생성 및 SecurityContext에 설정
        val authentication = UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.authorities
        )
        SecurityContextHolder.getContext().authentication = authentication
      }

    } catch (e: ExpiredJwtException) {
      // expired token 처리
      val message = e.message ?: "Access Token Expired"
      tokenError(response, message, objectMapper)
      return
    } catch (e: Exception) {
      val message = e.message ?: "Invalid Token"
      tokenError(response, message, objectMapper)
      return
    }

    // 필터 체인 진행
    filterChain.doFilter(request, response)
  }

  private fun resolveToken(request: HttpServletRequest): String? {
    val authorizationHeader = request.getHeader("Authorization")
    if (!authorizationHeader.isNullOrBlank() && authorizationHeader.startsWith("Bearer ")) {
      return authorizationHeader.substring(7) // "Bearer " 제거
    }
    return null
  }

}
