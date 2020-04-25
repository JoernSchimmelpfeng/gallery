package de.dhbw.gallery;

import io.micronaut.runtime.Micronaut;

/**
 * Main Application starting point
 */
@io.micronaut.core.annotation.TypeHint(typeNames = {"org.h2.Driver", "org.h2.mvstore.db.MVTableEngine"})
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}