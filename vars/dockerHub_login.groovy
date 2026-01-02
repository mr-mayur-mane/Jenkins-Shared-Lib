def call (){
    withCredentails([usernamePassword(credentailId: 'DockerHuB', usernameVariable: 'dockerHubUser', passwordVariable: 'dockerHubPass')])
    sh """
    docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}
    """
}