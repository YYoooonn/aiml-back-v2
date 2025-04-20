package org.aiml.user.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class CustomUserPrincipal(
  val userId: UUID,
  private val username: String,
  private val password: String,
  private val authorities: Collection<GrantedAuthority>
) : UserDetails {
  override fun getUsername() = username
  override fun getPassword() = password
  override fun getAuthorities() = authorities
  override fun isAccountNonExpired() = true
  override fun isAccountNonLocked() = true
  override fun isCredentialsNonExpired() = true
  override fun isEnabled() = true
}
