/* got one stage really but this will do for now */

pipeline {
    agent any

    stages {

        stage('Test') {
            steps {
                echo "Testing..."
                sh "/usr/local/bin/sbt test"
            }
        } /* end test */

    } /* end stages */
} /* end piplines */
