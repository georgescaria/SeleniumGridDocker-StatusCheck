pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                bat "docker build -t gscaria/seleniumtest ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'Dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push gscaria/seleniumtest:latest"
			    }                           
            }
        }
        stage('Start Selenium Grid') {
        	steps {
        		//sh
        		bat "docker compose up --no-color -d hub chrome firefox"
        		}
        }
        stage('Run Tests') {
        	steps {
        		//sh
        		bat "docker compose up tricentis1 tricentis2"
        		}
        }
    }
    post{
     	always{
     		archiveArtifacts artifacts: 'outputs/*'
        	bat "docker compose down"
        }
    }
}