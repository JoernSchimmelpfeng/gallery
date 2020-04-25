# de.dhbw.gallery

Database
See src/main/resource/application.yml to change production database settings 
In src/test/resource/application.yml database settings for unit test


On Windows you need to switch Jansi Logging to false:
    <withJansi>false</withJansi>

## Build and Deploy

./gradlew assemble
./docker-build.sh
./docker run -p 8080:8080 gallery
