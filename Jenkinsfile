pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                dir('student-backend') {
                    bat 'mvnw.cmd clean package'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('student-ui') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }
    }
}