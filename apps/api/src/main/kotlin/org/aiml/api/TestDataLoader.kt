package org.aiml.api

import jakarta.annotation.PostConstruct
import org.aiml.geometry.entity.Mesh
import org.aiml.geometry.repository.MeshRepository
import org.aiml.user.entity.User
import org.aiml.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class TestDataLoader(
  private val meshRepository: MeshRepository,
  private val userRepository: UserRepository
) {
  @PostConstruct
  fun loadTestData() {
    if (userRepository.count() == 0L) {
      userRepository.save(User(username = "Alice"))
      userRepository.save(User(username = "Bob"))
    }

    if (meshRepository.count() == 0L) {
      meshRepository.save(Mesh(name = "Cube"))
      meshRepository.save(Mesh(name = "Sphere"))
    }
  }
}
