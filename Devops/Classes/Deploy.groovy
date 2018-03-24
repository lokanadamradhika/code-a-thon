def deployCode(def workspace,def credentials)
{
	try
	{
		withCredentials([string(credentialsId: "${credentials}", variable: 'ServerPassword')])
		{
			copy = load "$workspace\\Devops\\Classes\\Commonmethods.groovy"
			bat """
				Net use * /delete /y
				NET USE \\\\${env.servername}\\IPC"\$" /USER:${env.serverusername} ${ServerPassword}
				mkdir \\\\${env.servername}\\D"\$"\\${env.previous2backup}
				robocopy \\\\${env.servername}\\D"\$"\\${env.previous1backup} \\\\${env.servername}\\D"\$"\\${env.previous2backup} /s
				robocopy \\\\${env.servername}\\D"\$"\\${env.latestbackup} \\\\${env.servername}\\D"\$"\\${env.previous1backup} /s
				robocopy \\\\${env.servername}\\D"\$"\\${env.deploymentlocation} \\\\${env.servername}\\D"\$"\\${env.latestbackup} /s
				Net use * /delete /y
			"""
				echo "test1"
				//Deploy
				try
				{
					echo "test"
					
					bat """NET USE \\\\${env.servername}\\IPC"\$" /USER:${env.serverusername} ${ServerPassword}"""
					bat """robocopy Artifacts\\${env.Environment}\\${env.folderversion}\\Deployment\\web1 \\\\${env.servername1}\\D"\$"\\${env.deploymentlocation} /s"""
					bat"""Net use * /delete /y"""
				}
				catch(Exception e)
				{
					echo "--------Failed to deploy the code. Rolling back the changes as a part of auto recovery---------"
					bat """
						NET USE \\\\${env.servername}\\IPC"\$" /USER:${env.serverusername} ${ServerPassword}
						robocopy \\\\${env.servername}\\D"\$"\\${env.latestbackup} \\\\${env.servername}\\D"\$"\\${env.deploymentlocation} /s
						robocopy \\\\${env.servername}\\D"\$"\\${env.previous1backup} \\\\${env.servername}\\D"\$"\\${env.latestbackup} /s
						robocopy \\\\${env.servername}\\D"\$"\\${env.previous2backup} \\\\${env.servername}\\D"\$"\\${env.previous1backup} /s
						rmdir /s /q \\\\${env.servername}\\D"\$"\\${env.previous2backup}
						Net use * /delete /y
					"""

				}
				bat """
					NET USE \\\\${env.servername}\\IPC"\$" /USER:${env.serverusername} ${ServerPassword}
					rmdir /s /q \\\\${env.servername}\\D"\$"\\${env.previous2backup}
					Net use * /delete /y
				"""
			
		}	
					
	}
	catch(Exception e)
	{
		
		echo "-------Failed to Deploy Code--------------"
		error e.message
	}
	finally
	{
		
	}
}
return this;



