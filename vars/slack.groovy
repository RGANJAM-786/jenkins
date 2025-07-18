def call(String buildStatus = 'STARTED') {
    // Default to SUCCESS if status is null or empty
    buildStatus = buildStatus ?: 'SUCCESS'

    // Default values (assume failure)
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"

    // Set color based on build status
    switch(buildStatus) {
        case 'STARTED':
            colorName = 'YELLOW'
            colorCode = '#FFFF00'
            break
        case 'SUCCESS':
            colorName = 'GREEN'
            colorCode = '#00FF00'
            break
        case 'FAILURE':
            colorName = 'RED'
            colorCode = '#FF0000'
            break
        default:
            colorName = 'GRAY'
            colorCode = '#AAAAAA'
            summary = "UNKNOWN STATUS: ${summary}"
    }

    // Send Slack notification
    slackSend(color: colorCode, message: summary)

    // Optionally log to Jenkins console
    echo "Slack notification sent: ${summary} with color ${colorName}"
}
