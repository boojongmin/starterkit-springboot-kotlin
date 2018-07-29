package boojongmin.server.entity

import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Audited
open class Auditable<U> {
    @CreatedBy
    private val createdBy: U? = null

    @CreatedDate
    private val createdDate: Date? = null

    @LastModifiedBy
    private val lastModifiedBy: U? = null

    @LastModifiedDate
    private val lastModifiedDate: Date? = null
}


@Entity
@Audited
data class Member(
        @Id @GeneratedValue(strategy = IDENTITY) var id: Long,
        var username: String?,
        var password: String?
): Auditable<String>() {
    constructor( username: String, password: String ): this(0L, username, password)
    constructor(): this(0L, null, null)
}

