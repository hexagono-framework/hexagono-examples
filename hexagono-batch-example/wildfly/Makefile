.PHONY: docker-build
docker-build:
	docker build -t hexagono-batch-example-wildfly .

.PHONY: docker-run
docker-run:
	docker run -it --rm -p 8080:8080 -p 9990:9990 -p 8787:8787 --name hexagono-batch-example-wildfly hexagono-batch-example-wildfly
	
.PHONY: docker
docker: docker-build docker-run