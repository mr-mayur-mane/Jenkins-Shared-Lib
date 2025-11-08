def call (){
    withCredentails([usernamePassword(credentailId: 'DockerHuB', usernameVariable: 'DockerHub', passwordVariable: 'DockerHubPass')])
    sh """
    docker login -u ${env.DockerHub} -p ${env.DockerHubPass}
    """
}