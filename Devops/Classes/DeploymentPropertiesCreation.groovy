String path
File file
def createDeploymentProperties(def workspace,def propertyfilepath)
{
	path="${workspace}\\Devops\\DeploymentLogs\\${env.Environment}"    
	file = new File(path)
	if (!file.exists()) 
	{
         file.mkdir()
    }
	props = new Properties()
	File check = new File("${workspace}\\${propertyfilepath}")
	if(check.exists())
	{
		 props.load(check.newDataInputStream())
		 def shacode = props.getProperty('LatestShaCode')
		 oldversion = props.getProperty('Version')
		 props.setProperty('PreviousShaCode',shacode)
		 props.setProperty('LatestShaCode',"${env.revisionID}")
		 props.setProperty('GitBranch',"${env.Branch}")
		 props.setProperty('PreviousVersion',"${oldversion}")
		 props.setProperty('Version',"${env.folderversion}")
		 props.store(check.newWriter(), null)
	}

	else
	{
		  OutputStream os 
		  props.setProperty('LatestShaCode',"${env.revisionID}")
		  props.setProperty('PreviousShaCode','')
		  props.setProperty('GitBranch',"${env.Branch}")
		  props.setProperty('Version',"${env.folderversion}")
		  os = new FileOutputStream("${workspace}\\${propertyfilepath}");
		  props.store(os,"Create Property File");
	}
	env.PreviousShaCode = props.getProperty('PreviousShaCode')
	env.LatestShaCode = props.getProperty('LatestShaCode')
}
return this;