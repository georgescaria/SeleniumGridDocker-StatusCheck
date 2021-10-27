FROM openjdk:8u212-jre-alpine3.9

#To check the hub sttaus we need curl and jq
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
ADD hubstatuscheck.sh					hubstatuscheck.sh
	
#Default Arguments
ARG BROWSER="firefox"
ENV BROWSER=${BROWSER}

ARG HUB_HOST="localhost"
ENV HUB_HOST=${HUB_HOST}

ARG TESTNGXML="Tricentis.xml"
ENV TESTNGXML=${TESTNGXML}

#Command to run hub status check, once check is done, selenium test is performed(from shell script)
ENTRYPOINT sh hubstatuscheck.sh
