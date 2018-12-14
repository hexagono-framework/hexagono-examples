.PHONY: docker-build
docker-build:
	docker build -t hexagono-batch-example-postgres .

.PHONY: docker-run
docker-run:
	docker run -it --rm -p 5432:5432 -e POSTGRES_DB=exampledb --name hexagono-batch-example-postgres hexagono-batch-example-postgres
	
.PHONY: docker
docker: docker-build docker-run