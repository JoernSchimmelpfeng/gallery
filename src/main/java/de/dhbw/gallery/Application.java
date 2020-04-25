package de.dhbw.gallery;

import io.micronaut.runtime.Micronaut;

/**
 * Main Application starting point
 */
@io.micronaut.core.annotation.TypeHint(typeNames = {"org.h2.Driver", "org.h2.mvstore.db.MVTableEngine"}) // needed for GraalVM
@io.swagger.v3.oas.annotations.OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "GalleryProject",
                version = "0.1",
                description = "Sample API for a Photogallery student project",
                license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "https://github.com/JoernSchimmelpfeng/gallery"),
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        url = "https://github.com/JoernSchimmelpfeng/gallery",
                        name = "Jörn Schimmelpfeng",
                        email = "schimmelpfeng@lehre.dhbw-stuttgart.de")
        )
)
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}