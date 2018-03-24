def copy
def createArtifactFolder(def workspace)
{
	try
	{
		bat """mkdir \"${workspace}\\Artifacts\\${env.Environment}\\${env.folderversion}\\Deployment\" ;\"${workspace}\\Artifacts\\${env.Environment}\\${env.folderversion}\\Logs\"; \"${workspace}\\Artifacts\\${env.Environment}\\${env.folderversion}\\DB\" """
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

def copyFilesToArtifacts(def workspace)
{
	try
	{
		copy = load "$workspace\\Devops\\Classes\\Commonmethods.groovy"
		copy.foldercopyoperation("$env.publishfolder","Artifacts\\${env.Environment}\\${env.folderversion}\\Deployment")
		copy.filecopyoperation("Devops\\DeploymentLogs\\${env.Environment}\\DeploymentDetails.properties","Artifacts\\${env.Environment}\\${env.folderversion}\\Logs")
		bat """git diff --name-status ${env.PreviousShaCode } ${env.LatestShaCode} >> \"${workspace}\\Artifacts\\${env.Environment}\\${env.folderversion}\\Logs\\DeploymentFiles.txt\""""
	}
	catch(Exception e)
	{
		echo "-------Failed to copy the required files to artifacts folder--------------"
		error e.message
	}
	finally
	{
	
	}
}

def zipArtifacts(def workspace)
{
	try
	{
		copy.filezipoperation("Artifacts\\${env.Environment}\\${env.folderversion}")
	}
	catch(Exception e)
	{
		echo "-------Failed to zip the artifacts--------------"
		error e.message
	}
	finally
	{
	
	}
}
return this;