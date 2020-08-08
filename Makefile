build-frontend:
	npm run install --prefix ./frontend
	npm run build:prod --prefix ./frontend


build-backend:
	./mvnw clean package

build: build-backend build-frontend