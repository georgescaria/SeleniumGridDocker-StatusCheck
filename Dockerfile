FROM openjdk:8u212-jre-alpine3.9

#To check the hub status we need curl and jq
RUN apk add curl jq

#Set workspace
WORKDIR /usr/share/udemy

#Add jars from target folder in host to image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							libs

#Add TestNG XML suite files
ADD Tricentis.xml 						Tricentis.xml
ADD Tricentis2.xml  					Tricentis2.xml					

#Add the shell script to check the hub status
#ADD hubstatuscheck.sh					hubstatuscheck.sh

# ADD health check script
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh
	
#Default Arguments
ARG BROWSER="chrome"
ENV BROWSER=${BROWSER}

ARG HUB_HOST="localhost"
ENV HUB_HOST=${HUB_HOST}

ARG MODULE="Tricentis.xml"
ENV MODULE=${MODULE}

#Command to run hub status check, once check is done, selenium test is performed(from shell script)
ENTRYPOINT sh healthcheck.sh
