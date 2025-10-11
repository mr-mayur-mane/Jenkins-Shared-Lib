def call (Map config=[:]){
    def imageName = config.imageName ?: error('Image name is required')
    def imageTag = config.imageTag ?: 'latest'
    def dockerFile = config.dokcerFile ?: 'Dockerfile'
    def context = config.context ?: '.'

    echo "Building Docker Image: ${imageName}:${imageTag} using ${dockerFile}"

    sh """
    docker build -t ${imageName}:${imageTag} -t ${imageName}:${imageTag} -f ${dockerFile} ${context}
    """
}