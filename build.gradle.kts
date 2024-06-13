plugins {
    id("java")
    id("application")
}

group = "com.distribuida"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    //Negocio CDI
    implementation("org.apache.openwebbeans:openwebbeans-impl:4.0.2")

    //La base de datos a través de JPA
    implementation("org.eclipse.persistence:eclipselink:4.0.3")

    //Componente(s) REST
    implementation("io.helidon:helidon-dependencies:4.0.9")
    implementation("io.helidon.webserver:helidon-webserver:4.0.9")

   //BD
    implementation("org.xerial:sqlite-jdbc:3.36.0.1")
    implementation("com.google.code.gson:gson:2.8.6")
}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main"))
    }
}

tasks.jar {
    manifest {
        attributes(
            mapOf("Main-Class" to "com.distribuida.Main",
                "Class-Path" to configurations.runtimeClasspath
                    .get()
                    .joinToString(separator = " ") { file ->
                        "${file.name}"
                    })
        )
    }
}

