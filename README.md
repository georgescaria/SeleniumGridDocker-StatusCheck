# SeleniumGridDocker-StatusCheck
Sample project to run automated scripts using Selenium in Selenium Grid using Docker.


### Steps to run:

1. Perform Maven clean package. This will compile and create the required jars and lib files(in /libs folder->added plugin in pom.xml) to run the scripts.

```mvn  
mvn clean package -DskipTest
```

2. Build the Dockerfile.

```docker  
docker build -t Imagename:tag .
```

The **ENTRYPOINT** is set to run shell script(**hubstatuscheck.sh**) from where the script is run after checking whether the Grid is up and running with ready value as **true**

3. Using **docker compose up** command, the following containers/services will be run in this Sample script:

	1. Selenium Grid - consistes of follwing services
		 i) Selenium Hub ii) Chrome node iii)) Firefox node
	2. Selenium script 1 (Tricentis.xml)
	3. Selenium script 2 (Tricentis2.xml)

```docker  
docker compose up
```
