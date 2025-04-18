package org.aiml.user.domain.exception

class UserNotFoundException(message: String) : RuntimeException(message)

class UserAlreadyExistsException(message: String) : RuntimeException(message)
