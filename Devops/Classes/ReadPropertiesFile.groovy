def Properties
def sonarurl
def readPropertiesFile(def filepath,def key)
{
	try
	{	
		props = new Properties()
		File globalProperties = new File(filepath)
		props.load(globalProperties.newDataInputStream())
		value = props.getProperty(key)
	}
	catch(Exception e)
	{
		echo "-------Failed to read the Properties file--------------"
		error e.message
	}
	finally
	{
	
	}
	return value
}
def callreadPropertiesMIOnline(def workspace,def path)
{
	try
	{
		propfile = load "${workspace}\\Devops\\Classes\\ReadPropertiesFile.groovy"
		env.sonarurl = propfile.readPropertiesFile(path,"sonarurl")
		echo "$env.sonarurl"
		env.sonarusername = propfile.readPropertiesFile(path,"sonarusername")
		env.sonarloglevel = propfile.readPropertiesFile(path,"sonarloglevel")
		env.sonarverbose = propfile.readPropertiesFile(path,"sonarverbose")
		env.sonaropencoverpath = propfile.readPropertiesFile(path,"sonaropencoverpath")
		env.sonarprojectname = propfile.readPropertiesFile(path,"sonarprojectname")
		env.sonarprojectversion = propfile.readPropertiesFile(path,"sonarprojectversion")
		env.majorversion = propfile.readPropertiesFile(path,"majorversion")
		env.minorversion = propfile.readPropertiesFile(path,"minorversion")
		env.assemblyfilepath = propfile.readPropertiesFile(path,"assemblyversionpath")
		env.msbuildnuggetpath = propfile.readPropertiesFile(path,"msBuildNuGetPath")
		env.slnpath = propfile.readPropertiesFile(path,"slnpath")
		env.npmpath = propfile.readPropertiesFile(path,"npmpath")
		env.npmdirectory = propfile.readPropertiesFile(path,"npmdirectory")
		env.msbuildpath = propfile.readPropertiesFile(path,"msBuildPath")
		env.testresultspath = propfile.readPropertiesFile(path,"testresultspath")
		env.transformationfilepath = propfile.readPropertiesFile(path,"transformationfilepath")
		env.transformationinputfilepath = propfile.readPropertiesFile(path,"transformationinputfilepath")
		env.transformationoutputfilepath = propfile.readPropertiesFile(path,"transformationoutputfilepath")
		env.transformationscriptpath = propfile.readPropertiesFile(path,"transformationscriptpath")
		env.dbtransformationinputfilepath = propfile.readPropertiesFile(path,"dbtransformationinputfilepath")
		env.dbtransformationoutputfilepath = propfile.readPropertiesFile(path,"dbtransformationoutputfilepath")
		env.dbtransformationscriptpath = propfile.readPropertiesFile(path,"dbtransformationscriptpath")
		env.dbprojectpath = propfile.readPropertiesFile(path,"dbpath")
		env.emailrecipients = propfile.readPropertiesFile(path,"emailrecipients")
		env.assemblycspath = propfile.readPropertiesFile(path,"assemblycsversionpath")
		env.builddirectory = propfile.readPropertiesFile(path,"builddirectory")
		env.publishfolder = propfile.readPropertiesFile(path,"publishfolder")
		env.servername = propfile.readPropertiesFile(path,"${env.Environment}_ServerName")
		echo "$env.servername"
		env.serverusername = propfile.readPropertiesFile(path,"${env.Environment}_UserName")
		env.deploymentlocation = propfile.readPropertiesFile(path,"${env.Environment}_DeploymentLocation")
		env.revisionidpath = propfile.readPropertiesFile(path,"revisionid")
		echo "$env.revisionidpath"
		env.latestbackup = propfile.readPropertiesFile(path,"latestbackup")
		env.previous1backup = propfile.readPropertiesFile(path,"previous1backup")
		env.previous2backup = propfile.readPropertiesFile(path,"previous2backup")
		env.apitransformationinputfilepath = propfile.readPropertiesFile(path,"apitransformationinputfilepath")
		env.apitransformationscriptpath = propfile.readPropertiesFile(path,"apitransformationscriptpath")
		env.jsonpropertiespath = propfile.readPropertiesFile(path,"jsonpropertiespath")
		
	}
	catch(Exception e)
	{
		echo "-------Failed to read the Properties file--------------"
		error e.message
	}
	finally
	{
	
	}
}
return this;