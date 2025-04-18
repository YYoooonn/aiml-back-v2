package org.aiml.api.exception

class InvalidTokenException(message: String) : RuntimeException(message)

class ExpiredJwtException(message: String) : RuntimeException(message)
