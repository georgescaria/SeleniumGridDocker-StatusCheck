#!/usr/bin/sh
# Environment Variables
# HUB_HOST
# BROWSER
# TESTNGXML
echo "Checking if hub is ready - $HUB_HOST = $( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )"
while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done
# start the java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=$HUB_HOST -DBROWSER=$BROWSER org.testng.TestNG $TESTNGXML