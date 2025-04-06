package org.aiml.libs.common.security

import org.springframework.stereotype.Component

@Component
class BcryptPasswordEncoder : PasswordEncoder {
  private val delegate = BcryptPasswordEncoder()
  override fun encode(rawPassword: CharSequence): String {
    return delegate.encode(rawPassword)
  }

  override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
    return delegate.matches(rawPassword, encodedPassword)
  }
}
