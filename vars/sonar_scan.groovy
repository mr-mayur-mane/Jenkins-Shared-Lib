def call(Map config = [:]){
    if (!config.projectKey){
        error(Missing SonarQube Project)
    }
    def scannerHome = tool 'SonarScanner'
    withSonarQubeEnv("sonarqube"){
        withCredentails([string(credentialsId: 'sonar-token', variable: 'ONAR_TOKEN')]){
            sh """
                ${scannerHome}/bin/sonar-scanner \
                  -Dsonar.projectKey=${config.projectKey} \
                  -Dsonar.sources=${config.sources ?: '.'} \
                  -Dsonar.host.url=${env.SONAR_HOST_URL} \
                  -Dsonar.login=$SONAR_TOKEN
            """
        }
    }
    // Quality gate check
    timeout(time: 2, unit: 'MINUTES') {
        def qg = waitForQualityGate()
        if (qg.status != 'OK') {
            error "Quality Gate Failed: ${qg.status}"
        }
    }
}