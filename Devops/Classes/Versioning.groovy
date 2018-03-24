def changeVersion(def workspace)
{
	try
	{
		withEnv(["major_revision=${env.majorversion}",
										"minor_revision=${env.minorversion}",
												"path=${workspace}\\${env.assemblyfilepath}"
												]) 
					{
						powershell(". '${workspace}\\Devops\\Versioning\\updateassemblyversion.ps1'")
					}
		
	}
	catch(Exception e)
	{
		echo "-------Failed to change the assembly version in vb file--------------"
		error e.message
	}
	finally
	{
	
	}
}
def changeVersionCS(def workspace)
{
	try
	{
		withEnv(["major_revision=${env.majorversion}",
										"minor_revision=${env.minorversion}",
												"path=${workspace}\\${env.assemblycspath}"
												]) 
					{
						powershell(". '${workspace}\\Devops\\Versioning\\updateversioncs.ps1'")
					}
		
	}
	catch(Exception e)
	{
		echo "-------Failed to change the assembly version in cs file--------------"
		error e.message
	}
	finally
	{
	
	}
}
def folderVersion(def workspace)
{
	try
	{
		def date = new Date()
		def build = (((date[Calendar.YEAR])-2000)*366)+date[Calendar.DAY_OF_YEAR]
		env.buildnumber =Integer.toString(build)
		env.folderversion = "$env.majorversion"+'.'+"$env.minorversion"+'.'+"$env.buildnumber"+'.'+"$env.BUILD_NUMBER"+'_'+"$env.revisionID"
		echo "$env.folderversion"
	}
	catch(Exception e)
	{
		echo "-------Failed to set the folder version--------------"
		error e.message
	}
	finally
	{
	
	}
	
}
return this;