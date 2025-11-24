def call(Map args = [:]) {

    // Required param
    if (!args.target) {
        error "trivyScan: 'target' is required"
    }

    // Parameters with defaults
    String target    = args.target                     // image or path
    String type      = args.get('type', 'image')       // image | fs
    String severity  = args.get('severity', 'HIGH,CRITICAL')
    boolean fail     = args.get('failOnVuln', true)

    // Output report filename
    String reportName = "trivy-report-${target.replaceAll(/[^A-Za-z0-9]/, '_')}.txt"

    // Build scan command
    String command = ""

    if (type == 'image') {
        command = """
            trivy image \
            --severity ${severity} \
            --format table \
            --output ${reportName} \
            ${fail ? '--exit-code 1' : '--exit-code 0'} \
            ${target}
        """
    } else if (type == 'fs') {
        command = """
            trivy fs \
            --severity ${severity} \
            --format table \
            --output ${reportName} \
            ${fail ? '--exit-code 1' : '--exit-code 0'} \
            ${target}
        """
    } else {
        error "trivyScan: Invalid type '${type}'. Use 'image' or 'fs'."
    }

    // Execute scan
    sh """
        echo "===== Trivy Scan Started ====="
        echo "Target: ${target}"
        echo "Scan Type: ${type}"
        echo "Severity: ${severity}"
        ${command}
        echo "===== Trivy Scan Completed ====="
    """

    // Save report in Jenkins
    archiveArtifacts artifacts: reportName, fingerprint: true

    echo "Trivy report saved: ${reportName}"
}
