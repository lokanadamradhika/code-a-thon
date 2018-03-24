def sonarAnalysisCSharp(def workspace,def passwordsonar)
{
	try
	{
		withSonarQubeEnv('SonarQubeMSBuild')
		{
			withCredentials([string(credentialsId: "${passwordsonar}", variable: 'sonarpassword')]) 
			{
				bat "\"${env.SonarMsBuild}\\SonarQube.Scanner.MSBuild.exe\" begin /k:${env.sonarprojectname} \
				/n:${env.sonarprojectname} \
				/v:${env.sonarprojectversion} \
				/d:sonar.host.url=${env.sonarurl} \
				/d:sonar.login=${env.sonarusername} \
				/d:sonar.password=${sonarpassword} \
				/d:sonar.log.level=${env.sonarloglevel} \
				/d:sonar.verbose=${env.sonarverbose} \
				/d:sonar.cs.opencover.reportsPaths=${env.sonaropencoverpath}"
			}
			echo "test"
			bat env.msBuildNuGetCmd
			buildCmd = "\"${env.msBuildPath}\" \"${workspace}\\${env.slnpath}\" /p:Configuration=Release /t:Rebuild"
			bat buildCmd
			dir("${env.testresultspath}")
			{
				common = load "$workspace\\Devops\\Classes\\Commonmethods.groovy"
				common.filedeleteoperation("testresults.trx")
				
				bat """ "C:\\Program Files (x86)\\opencover\\OpenCover.Console.exe" \
				-register:admin -target:"C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\TestAgent\\Common7\\IDE\\MSTest.exe" \
				-targetargs:/testcontainer:MIOnline.Services.Tests.dll -targetargs:/resultsfile:testresults.trx \
				-"filter:+[*]* -[*.pdb]*" -output:coverage.xml -mergebyhash """
			}	
			
				common.filecopyoperation("${env.testresultspath}\\coverage.xml", ".")
				common.filecopyoperation("${env.testresultspath}\\testresults.trx", ".")
			
			bat "\"${env.SonarMsBuild}\\SonarQube.Scanner.MSBuild.exe\" end"
			/*timeout(time:1, unit: 'MINUTES') 
			{ 
				def qg = waitForQualityGate() 
				if ((qg.status != 'OK')) 
				{
					echo "----------------C# Quality Gate Failure-------------------------"
					error "Pipeline aborted due to following quality gate failure: StaticScan=${qg.status}"
				}              
									
			}*/
		}
	}
	catch(Exception e)
	{
		echo "-------Failed to perform sonar analysis on C# Code--------------"
		error e.message
	}
	finally
	{
	
	}
}
return this;