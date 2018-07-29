package boojongmin.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import java.util.*
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfiguration {

    @Bean
    fun auditorProvider() = AuditorAware<String> {
        val name = SecurityContextHolder.getContext()?.authentication?.name ?: "system"
        Optional.of(name)
    }
}
