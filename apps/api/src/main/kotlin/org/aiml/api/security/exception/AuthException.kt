package org.aiml.api.security

sealed class AuthException(message: String) : RuntimeException(message)

class InvalidTokenException(message: String) : AuthException(message)
class ExpiredJwtException(message: String) : AuthException(message)
