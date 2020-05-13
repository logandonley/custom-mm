# custom-mm

This repo is a starting point to building your own custom managed master images. You've got a plugins.txt file and a jcasc.yaml file which can be configured as needed.

To grab a list of installed plugins on an existing master, you can run the following from the `Managed Jenkins > Script console`

```groovy
def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()
plugins.each {println "${it.getShortName()}:${it.getVersion()}"}
```


*Important note* - because the jcasc file needs to be mounted into the $JENKINS_HOME directory, we can't directly copy it from the Dockerfile since the Jenkins home directory gets mapped to a volume in Kubernetes. 

To solve overcome this, we have the `init.groovy.d/init_07_casc.groovy` script which gets run on launch which grabs the url of this jcasc.yaml file and loads it into JCasC. There are many ways to do this, but this seems like the simplest.

Alternatively, you could have a job on an *"ops"* master which pulls the config in a job and then runs the configuration against the master.