pipeline {
  agent any

  environment {
    DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
    VERSION = "${env.BUILD_ID}"
    

  }

  tools {
    maven "Maven"
  }

  stages {

    stage('Maven Build') {
      steps {
        sh 'mvn clean package  -DskipTests'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('SonarQube Analysis') {
      steps {
        sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://18.168.154.40:9000/ -Dsonar.login=squ_e360c43bfffe41a497aa4fd6f06f1026504f8534'
      }
    }

    stage('Check code coverage') {
      steps {
        script {
          def token = "squ_e360c43bfffe41a497aa4fd6f06f1026504f8534"
          def sonarQubeUrl = "http://18.168.154.40:9000/api"
          def componentKey = "com.restaurantlisting:project"
          def coverageThreshold = 70.0

          def response = sh(
            script: "curl -H 'Authorization: Bearer ${token}' '${sonarQubeUrl}/measures/component?component=${componentKey}&metricKeys=coverage'",
            returnStdout: true
          ).trim()

          def coverage = sh(
            script: "echo '${response}' | jq -r '.component.measures[0].value'",
            returnStdout: true
          ).trim().toDouble()

          echo "Coverage: ${coverage}"

          if (coverage < coverageThreshold) {
            error "Coverage is below the threshold of ${coverageThreshold}%. Aborting the pipeline."
          }
        }
      }
    }

    stage('Docker Build and Push') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
        sh 'docker build -t rasukuntanagesh/restaurant-listing-service:${VERSION} .'
        sh 'docker push rasukuntanagesh/restaurant-listing-service:${VERSION}'
      }
    }

    stage('Cleanup Workspace') {
      steps {
        deleteDir()

      }
    }

    stage('Update Image Tag in GitOps') {
      steps {
        checkout scmGit(branches: [
          [name: '*/main']
        ], extensions: [], userRemoteConfigs: [
          [credentialsId: 'git-ssh', url: 'https://github.com/nageshgitversion/deployment-folder.git']
        ])
        script {
          sh '''
          
          sed -i "s/image:.*/image: rasukuntanagesh\\/restaurant-listing-service:${VERSION}/" aws/restaurant-manifest.yml
          
          '''
          sh 'git checkout main'
          sh 'git add .'
          sh 'git commit -m "Update image tag"' 
          sshagent(['git-ssh']) { 
            sh('git push':${nageshgitversion}:${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})
       
          } 
        }
      }
    }

  }

}
