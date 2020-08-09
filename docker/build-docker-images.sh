#!/bin/sh

docker build -t marceloleite2604/iss-tracker-backend $(dirname $0)/../backend
# docker create --name iss-tracker-backend -e SLED_ENCRYPTION_KEY=? marceloleite2604/iss-tracker-backend

docker build -t marceloleite2604/iss-tracker-inquisitor $(dirname $0)/../inquisitor
# docker create --name iss-tracker-inquisitor -e SLED_ENCRYPTION_KEY=? -e GOOGLE_API_KEY=? marceloleite2604/iss-tracker-inquisitor