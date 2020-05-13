# custom-mm

This repo is a starting point to building your own custom managed master images. You've got a plugins.txt file and a jcasc.yaml file which can be configured as needed.

To grab a list of installed plugins on an existing master, you can run the following from the `Managed Jenkins > Script console`

```groovy
def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()
plugins.each {println "${it.getShortName()}:${it.getVersion()}"}
```

## How to use

1. Customize the plugins.txt file with the plugins you want to ensure are installed. When you generate that list of plugins using the script above, you'll get a lot of output, lots of these get preinstalled or are dependencies and don't need to be defined here.
2. Customize the jcasc.yaml file with the configuration you want to add. Using an existing master (with the **configuration as code** plugin installed) you can grab the current configuration export and stick the important pieces int he jcasc.yaml file. *Important note* - not all configuration is safe to be copied over, try to use only the required changes you actually need.
3. Build this Docker image (e.g. `docker build -t <registry>/<org>/<image_name>:<tag> .`) and then push it to a registry that your Kubernetes cluster can talk to.
4. On CJOC, go to `Manage Jenkins > Configure System > Container Master Provisioning > Show docker images` and add a new entry with a name of your choice and the image you pushed.
5. Return to the home page of CJOC and create a new managed master. There will be a `Docker image` parameter you can change to your new option.
6. Connect to the new master and you should have your configuration set automatically.
