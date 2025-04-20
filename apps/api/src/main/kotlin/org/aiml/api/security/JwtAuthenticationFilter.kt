package org.aiml.api.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.*
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
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
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(e.message ?: "Access Token Expired")
            return
        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(e.message ?: "Invalid Token")
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
