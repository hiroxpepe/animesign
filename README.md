# AnimeSign
## Motion Comic Generator for a Web browser.
![game image](https://i.imgur.com/auwRGzK.png)

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
#### build the app by using npm and maven.

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

- check if it can access and to the next step.  
http://localhost/app

You may get an error page because Jetty is not ready to start up soon, so please wait a minute.

## How to play the AnimeSign app.
+ press the **build** button on the upper left of the screen.
+ wait some seconds until show the **play** button.
+ press the **play** button then the comic will be started.

## License
+ [Apache License](https://www.apache.org/licenses/LICENSE-2.0).