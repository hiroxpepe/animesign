# AnimeSign Project Map

## Overview
It's a Web application that displays like motion comic animation on a Web browser.

## Content
- docker : WEB Server by Nginx.
- source : APP Server by Jetty-plugin.
- var/www/html : Resource files.

## What tools to use.
- Maven (* also JDK)
- npm
- Docker

## How to build.
```sh
$ cd PathToThisREADME.md
$ cd source
$ mvn install
```

## How to run.
### terminal 1 : run web server.  
- run
```sh
$ cd PathToThisREADME.md
$ cd docker
$ docker-compose up -d
Creating animesign_nginx ... done
```
- check if it can access.  
http://localhost/index.html  

### terminal 2 : run app server.
- run
```sh
$ cd PathToThisREADME.md
$ cd source/webapp
$ mvn jetty:run
```
- check if it can access.  
http://localhost:8080/animesign/

## How to play the AnimeSign app.
- Press the **build** button on the upper left of the screen.
- Press the **play** button on the upper left of the screen.

## How to deploy to the other environment as a docker image.
- TBA

## license
- TBA
