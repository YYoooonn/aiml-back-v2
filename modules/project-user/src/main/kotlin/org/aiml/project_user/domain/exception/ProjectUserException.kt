package org.aiml.project_user.domain.exception

class NotAuthorizedException(message: String) : RuntimeException(message)

class UserAlreadyExistsException(message: String) : RuntimeException(message)
