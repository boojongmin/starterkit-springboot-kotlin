package boojongmin.server.repository

import boojongmin.server.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRespository: JpaRepository<Member, Long> {
    fun findByUsername(username: String?): Member
}