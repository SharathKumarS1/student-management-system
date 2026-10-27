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

        stage('API Automation Tests') {
            steps {
                dir('student-api-tests') {
                    bat 'npm install'
                    bat 'npx playwright test'
                }
            }
        }

        stage('UI Automation Tests') {
            steps {
                dir('student-ui-tests') {
                    bat 'npm install'
                    bat 'npx playwright install'
                    bat 'npx playwright test'
                }
            }
        }
    }
}