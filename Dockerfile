FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/gallery
WORKDIR /home/app/gallery

RUN native-image --no-server --report-unsupported-elements-at-runtime --initialize-at-build-time=org.h2.Driver --static -cp build/libs/de.dhbw.gallery-*-all.jar

FROM scratch
EXPOSE 8280
MAINTAINER Jörn Schimmelpfeng
COPY --from=graalvm /home/app/gallery/gallery /app/gallery
ENTRYPOINT ["/app/gallery", "-Djava.library.path=/app"]