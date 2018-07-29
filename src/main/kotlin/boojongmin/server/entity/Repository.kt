package boojongmin.server.entity

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRespository: JpaRepository<Member, Long> {
    fun findByUsername(username: String?): Member
}