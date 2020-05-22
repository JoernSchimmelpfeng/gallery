#!/bin/sh
docker build . -t=gallery || exit 1
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8280:8280 --name gallery gallery"
