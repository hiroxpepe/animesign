# AnimeSign
## Motion Comic Generator for a Web browser.
![game image](https://i.imgur.com/MLwz2nx.png)

## Overview
It's a Web application that displays like a motion comic animation on the Web browser.

## Content
- docker : a web server by Nginx and an app server by Jetty.
- source : the source code of the war app for Jetty.
- var/www/html : Resource files.

## What tools to use.
- Maven (* also JDK)
- npm
- Docker

## How to build.
```sh
$ cd PathToThisREADME.md
$ cd source/webapp
$ npm install
$ npm run build
$ cd ..
$ mvn install
```

## How to run.
#### run the web server and the app server via docker.
```sh
$ cd PathToThisREADME.md
$ cd docker
$ docker-compose up -d
Creating animesign_jetty ... done
Creating animesign_nginx ... done
```
- check if it can access.  
http://localhost/index.html  

- check if it can access and to the next step.  
http://localhost:8080/animesign/

## How to play the AnimeSign app.
- press the **build** button on the upper left of the screen.
- press the **play** button on the upper left of the screen.

## How to deploy to the other environment as a docker image.
- TBA

## License
It's released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
