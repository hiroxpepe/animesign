# AnimeSign
## Motion Comic Generator for a Web browser.
![game image](https://i.imgur.com/MLwz2nx.png)

## Overview
It's a Web application that displays like a motion comic animation on the Web browser.

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
### Terminal 1 : run web server via docker.
```sh
$ cd PathToThisREADME.md
$ cd docker
$ docker-compose up -d
Creating animesign_nginx ... done
```
- check if it can access.  
http://localhost/index.html  

### Terminal 2 : run app server via jetty:run.
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

## License
It's released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
