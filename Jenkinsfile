pipeline {
    agent any
    tools {
        jdk 'Jdk11'
   }
    stages {
        stage("compilar") {
            steps {
                dir("vaquesrest") {
                    withMaven() {
                        sh 'mvn compile'
                    }
                }
            }
        }
        stage("tests") {
            steps {
                dir("vaquesrest") {
                    sh "mvn test"
                }
            }
        }
        stage("Mes coses") {
            steps {
                echo "Més coses"
            }
        }
    }
}