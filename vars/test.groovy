def call(Map config[:]){
    def dokcerImage = config.dockerImage ?: error('Image is required')
    def dockerTag = config.dockerTag ?: 'latest'
    def dockerFile = config.dokcerFile ?: 'Dockerfile'
    def context = config.context ?: '.'
    sh """
    docker build -t ${dockerImage}:${dockerTag} -t ${dockerImage}:${dockerTag} -f ${dockerFile} ${context}
    """
}