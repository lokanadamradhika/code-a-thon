def checkout (def branch,checkoutCredentials,projectUrl) 
{
	try
	{
		checkout([
			$class: 'GitSCM', 
			branches: [[name: branch]],		
			userRemoteConfigs: [[
			credentialsId:checkoutCredentials, 
			url: projectUrl
			]],
			extensions: [[
			$class: 'LocalBranch', 
			localBranch: "**"
			]]
		])
	}
	catch(Exception e)
	{
		echo "-------Failed in Checkout Stage--------------"
		error e.message
	}
	finally
	{
		
	}
}
return this;