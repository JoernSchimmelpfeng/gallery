package de.dhbw.gallery

import io.micronaut.core.util.AntPathMatcher
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.net.URI

/**
 * Home redirection to swagger api documentation
 */
@Controller
class HomeController {
    @Get(AntPathMatcher.DEFAULT_PATH_SEPARATOR)
    @Produces(MediaType.TEXT_HTML)
    fun index(): HttpResponse<String> {
        return HttpResponse.redirect(URI.create("/swagger-ui"))
    }
}