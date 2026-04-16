def call(){
    echo "Hello, this is shared library for jenkins "
    git url: ${params.GIT_URL}, branch: ${params.GIT_BRANCH}
}