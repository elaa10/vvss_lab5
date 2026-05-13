pipeline {
    agent any

    tools {
        // Name must match the JDK configured in Jenkins → Global Tool Configuration
        jdk 'JDK8'
        maven 'Maven3'
    }

    environment {
        // headless Chrome for CI – no display needed
        HEADLESS = 'true'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                // Run all tests; -Dwebdriver.driver=chrome selects Chrome
                // -Dheadless.mode=true runs without a visible window (CI-friendly)
                sh 'mvn clean verify -Dwebdriver.driver=chrome -Dheadless.mode=true'
            }
        }

        stage('Generate Serenity Report') {
            steps {
                sh 'mvn serenity:aggregate'
            }
        }
    }

    post {
        always {
            // Publish the Serenity HTML report in Jenkins
            publishHTML(target: [
                allowMissing         : false,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : 'target/site/serenity',
                reportFiles          : 'index.html',
                reportName           : 'Serenity BDD Report'
            ])
        }
        failure {
            echo 'Tests failed – check the Serenity report for details.'
        }
    }
}
