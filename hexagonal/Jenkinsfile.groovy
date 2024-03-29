pipeline {
    agent any
    stages {
        stage('git') {
            steps {
                git branch: 'master',
                        url: 'git@github.com:cieplakm/playground.git'
            }
        }
        stage('unit tests') {
            steps {
                dir("hexagonal") {
                    sh 'mvn clean test'
                }
            }
        }
        stage('integration tests') {
            steps {
                dir("hexagonal") {
                    sh 'mvn clean verify'
                }
            }
        }
        stage('package') {
            steps {
                dir("hexagonal") {
                    sh 'mvn install -DskipTests'
                }
            }
        }
        stage('deploy secrets') {
            steps {
                dir("hexagonal/module/application") {
                    sh 'flyctl secrets set APP_NAME=app_anaconda_from_$(date +%Y%m%d%H%M%S)'


                }
            }
        }
        stage('deploy') {
            steps {
                dir("hexagonal/module/application") {
                    sh 'flyctl deploy'
                }
            }
        }
    }
}