def call(String buildStatus = 'STARTED') {
    buildStatus = buildStatus ?: 'SUCCESS'

    def colorCode = '#FF0000'  // default RED
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"

    switch(buildStatus) {
        case 'STARTED':
            colorCode = '#FFFF00' // Yellow
            break
        case 'SUCCESS':
            colorCode = '#00FF00' // Green
            break
        case 'FAILURE':
            colorCode = '#FF0000' // Red
            break
        default:
            colorCode = '#AAAAAA' // Gray
    }

    slackSend(color: colorCode, message: summary)
    echo "Slack notification sent: ${summary}"
}
