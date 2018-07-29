package boojongmin.server.config

import boojongmin.server.repository.MemberRespository
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*


@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/favicon.png", "/assets/**")
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                .defaultSuccessUrl("/admin", true)
            .permitAll()
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
    }
}

@Service
class MemberDetailsService(val repo: MemberRespository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return Optional.ofNullable(repo.findByUsername(username))
                .map {
                    User.builder()
                            .username(it.username)
                            .password("""{noop}${it.password}""")
                            .roles("ADMIN").build()
                }.orElse(null)
    }
}
