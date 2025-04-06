package org.aiml.api.controller

import org.aiml.geometry.repository.MeshRepository
import org.aiml.user.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val userRepository: UserRepository,
    private val meshRepository: MeshRepository
) {
    @GetMapping("/test/user")
    fun getUsers() = userRepository.findAll()

    @GetMapping("/test/mesh")
    fun getMesh() = meshRepository.findAll()
}
