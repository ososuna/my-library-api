pipeline {
  agent {
    any
  }
  environment {
    BRANCH_NAME = "${env.BRANCH_NAME}"
  }
  stages {
    stage('Build project') {
      steps {
        echo "Building project"
        sh 'mvn package'
      }
    }
    stage('Build docker image') {
      when {
        anyOf {
          branch 'master'
          branch 'develop'
        }
      }
      steps {
        echo "Building docker image"
        sh 'docker build -t spring-book-api .'
      }
    }
    stage('Deploy to docker hub') {
      when {
        anyOf {
          branch 'master'
          branch 'develop'
        }
      }
      steps {
        echo "Deploying to docker hub"
        // sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'
        // sh 'docker tag spring-book-api $DOCKER_USER/spring-book-api:$BRANCH_NAME'
        // sh 'docker push $DOCKER_USER/spring-book-api:$BRANCH_NAME'
      }
    }
    stage('Deploy to AWS') {
      when {
        branch 'master'
      }
      steps {
        echo "Deploying to AWS"
        // sh 'aws ecs update-service --cluster spring-book-api-cluster --service spring-book-api-service --force-new-deployment'
      }
    }
  }
}