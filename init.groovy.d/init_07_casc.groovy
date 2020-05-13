
import io.jenkins.plugins.casc.ConfigurationAsCode;

import java.util.logging.Logger

String scriptName = "init_07_casc.groovy"

Logger logger = Logger.getLogger(scriptName)

logger.info("attempting to load config as code from /usr/share/jenkins/jcasc.yaml")
ConfigurationAsCode.get().configure("/usr/share/jenkins/jcasc.yaml")