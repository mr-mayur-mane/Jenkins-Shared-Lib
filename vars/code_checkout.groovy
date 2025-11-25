def call (String GitUrl, String GitBranch){
    git url: "${GitUrl}", branch: "${env.GitBranch}"
}