# de.dhbw.gallery

Database
See src/main/resource/application.yml to change production database settings 
In src/test/resource/application.yml database settings for unit test


On Windows you need to switch Jansi Logging to false:
    <withJansi>false</withJansi>

## Build and Deploy as Container

Configuration right now assumes that the H2 database is running standalone since H2 does 
not work with GraalVM.

./gradlew assemble
./docker-build.sh
./docker run -p 8280:8280 --name gallery gallery
