def call(String Project, String ImageTag, String dockerHubPass){
    echo "Pushing to docker hub"
    withCredentials([usernamePassword(credentialsId: "dockerHub",passwordVariable: "dockerHubPass", usernameVariable: "dockerHubUser")]){
    sh "docker tag my-note-app ${env.dockerHubUser}/my-note-app:latest"
    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"
    sh "docker push ${env.dockerHubUser}/my-note-app:latest"
    }
}