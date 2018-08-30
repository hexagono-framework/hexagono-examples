.PHONY: docker-build
docker-build:
	docker build -t hexagono-example-webapplication-wildfly .

.PHONY: docker-run
docker-run:
	docker run -it --rm -p 8080:8080 -p 9990:9990 -p 8787:8787 --name hexagono-example hexagono-example-webapplication-wildfly
