package org.aiml.libs.common.security.jwt

import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtProvider: JwtProvider
) {
    private val accessTokenValidity = 1000 * 60 * 30L  // 30분
    private val refreshTokenValidity = 1000 * 60 * 60 * 24 * 7L  // 7일

    fun generateAccessToken(id: UUID, username: String): String {
        return jwtProvider.generateToken(
            mapOf("sub" to id, "type" to "access", "username" to username),
            accessTokenValidity
        )
    }

    fun generateRefreshToken(id: UUID, username: String): String {
        return jwtProvider.generateToken(
            mapOf("sub" to id, "type" to "refresh", "username" to username),
            refreshTokenValidity
        )
    }

    fun validateAccessToken(token: String): Boolean {
        val claims = jwtProvider.getClaims(token)
        return jwtProvider.isValidToken(token) && claims["type"] == "access"
    }

    fun validateRefreshToken(token: String): Boolean {
        val claims = jwtProvider.getClaims(token)
        return jwtProvider.isValidToken(token) && claims["type"] == "refresh"
    }

    fun getUsernameFromToken(token: String): String {
        return jwtProvider.getClaims(token)["username"] as String
    }

    fun getUserIdFromToken(token: String): String {
        return jwtProvider.getClaims(token).subject
    }

    fun getUserClaimsFromToken(token: String): Pair<String, String> {
        val claims = jwtProvider.getClaims(token)
        return claims.subject to claims["username"] as String
    }
}
