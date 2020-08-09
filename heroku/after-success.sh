#!/bin/sh

make -C $(dirname $0)/.. build-docker-images
echo "$DOCKER_PASSWORD" | docker login --username _ --password-stdin registry.heroku.com
docker tag marceloleite2604/iss-tracker-inquisitor registry.heroku.com/marceloleite2604-iss-tracker/worker
docker push registry.heroku.com/marceloleite2604-iss-tracker/worker
docker tag marceloleite2604/iss-tracker-backend registry.heroku.com/marceloleite2604-iss-tracker/web
docker push registry.heroku.com/marceloleite2604-iss-tracker/web