def filedeleteoperation(String path)
{
 fileOperations([fileDeleteOperation(excludes: '', includes: path)])
}

def filecopyoperation (String source, String target, Boolean flatten = 'true') {
	fileOperations([

		fileCopyOperation(
			excludes: '', 
			flattenFiles: flatten, 
			includes: source, 
			targetLocation: target
		)

	])
}

def getRevisionID(def workspace)
{
	File completerevisionid = new File("${workspace}\\${env.revisionidpath}\\${env.Branch}")
	env.revisionID = completerevisionid.text.substring(0,7)
	echo "$env.revisionID"
}

def foldercopyoperation (String source, String target) {
	fileOperations([

		folderCopyOperation(
			destinationFolderPath: target, 
			sourceFolderPath: source
		)

	])
}

def filezipoperation (String path) {
	fileOperations([

		fileZipOperation(path)
	])
}

def emailnotification(String buildstatus) 
{
	emailext (
	subject: "Jenkins job Name:: ${env.JOB_NAME}- Build Status: "+currentBuild.currentResult,
body: 
"""
PROJECT :${env.JOB_NAME} \n
BUILD NUMBER :${env.BUILD_NUMBER} \n
Build Status :${currentBuild.currentResult} \n 
Check console output at : ${env.BUILD_URL}console
""",
	to: "$env.emailrecipients",
	attachLog: true
	 )
 }

return this;