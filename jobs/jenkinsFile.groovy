//@Library('shared') _  // Load the lib library

import deploy.Deploy
import deploy.DeployZ
import org.Organize

pipeline {
    agent {
        label 'master' // Replace with your desired node label
    }

    // Parameters
    parameters {
        string(name: 'BRANCH', defaultValue: 'master', description: 'Branch to build')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Whether to run tests')
    }

    // Options
    options {
        timeout(time: 30, unit: 'MINUTES') // Build timeout
        buildDiscarder(logRotator(numToKeepStr: '10')) // Keep last 10 builds
    }

    // Global environment variables
    environment {
        ENV_VAR_1 = 'value1'
        ENV_VAR_2 = 'value2'
    }

    stages {
        stage('Setup') {
            steps {
                script {
                    def utils = new Stuff(this)
                    utils.print()
//                    lib.stuff(this)
                    def deploy = new Deploy(this)
                    deploy.println()
                    def deployz = new DeployZ(this)
                    deployz.println()
                    def orga = new Organize(this)
                    orga.println()
                }
            }
        }
    }
    post {
        always {
            script {
                cleanWs()
            }
        }
    }
}