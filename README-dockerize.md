# Introduction

This document will describe how to containerize 

## Dockerfile

Following is a Dockerfile 

```
FROM openjdk:8-jdk-alpine
 
COPY ${PWD}/msr-api/target/msr-api-1.0.0.jar /deployments/

CMD java -jar -Dspring.profiles.active=docker /deployments/msr-api-1.0.0.jar

```

You can run the command below to dockerize the MSR API application

```
$ docker build -t msr-api:1.0.0 .
```


### Push image to registry
```
$ docker push credemol/msr-api:20170921
```

## docker-compose.yml

```
version: '2'

services:
  db:
    image: mysql:5.7.13
    ports:
      - "3306:3306"
    volumes:
      - msr_db:/var/lib/mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: KimKyudong1!
      MYSQL_DATABASE: MSR_DB
      MYSQL_USER: msrdb
      MYSQL_PASSWORD: KimKyudong1!

  msr-api:
    depends_on:
      - db    
    image: msr-api:1.0.0
    ports:
      - "8080:8080"
    restart: always

volumes:
  msr_db:
```


## How to run mysql container without building your own docker image
This section is just for information.

```
$ docker volume ls
$ docker volume create msr_db
$ docker run -d \
    -e MYSQL_ROOT_PASSWORD=KimKyudong1! \
    -e MYSQL_USER=msrdb \
    -e MYSQL_PASSWORD=KimKyudong1! \
    -e MYSQL_DATABASE=MSR_DB \
    -p 3306:3306 \
    -v msr_db:/var/lib/mysql \
    --name msrdb_mysql \
    "mysql:5.7.13"
```

