pipeline {
    agent any
    
    stages {
        stage("compilar") {
            steps {
                sh 'mvn compile'
            }
        }
        stage("tests") {
            steps {
                    sh "mvn test"
            }
        }
        stage("Mes coses") {
            steps {
                echo "Més coses"
            }
        }
    }
}