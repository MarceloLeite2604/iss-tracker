build-frontend:
	npm install --prefix ./frontend
	npm run build:prod --prefix ./frontend

build-backend:
	./mvnw -P heroku clean package

build-app: build-backend build-frontend

build-docker-images:
	./docker/build-docker-images.sh