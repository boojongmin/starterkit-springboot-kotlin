package boojongmin.server

import boojongmin.server.domain.Member
import boojongmin.server.repository.MemberRespository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ServerApplication {

    @Bean
    fun runner(repo: MemberRespository) = CommandLineRunner {
        repo.saveAll(
                arrayListOf(
                        Member("username1", "password1"),
                        Member("username2", "password2"),
                        Member("username3", "password3"),
                        Member("username4", "password4"),
                        Member("username5", "password5")
                )
        )
        repo.findById(1)
                .ifPresent {
                    it.username = "update username1"
                    it.changeState(Member.State.ACTIVE)
                    repo.save(it)
                }
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}


