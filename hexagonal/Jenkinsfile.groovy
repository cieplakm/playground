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
    }
}