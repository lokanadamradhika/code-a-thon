$ErrorActionPreference="Stop"

function Get-VersionValue([string]$OldValue, [string]$NewValue) {
    if (-not $NewValue) {
        return $OldValue
    }

    # Placeholder for other functionality, like incrementing, dates, etc..
    if ($NewValue -eq "increment") {
        [long]$newNum = 1

        if (-not [Int64]::TryParse($OldValue, [ref]$newNum)) {
            $newNum = 1
        }

        return $newNum.ToString()
    }
    else {
        return $NewValue
    }
}

function Set-AssemblyFileVersion([string]$PathToFile, [string]$MajorVer, [string]$MinorVer, [string]$BuildVer, [string]$RevVer) {
    # Load the file and process the lines.
    $newFile = Get-Content $PathToFile -Encoding "UTF8" | ForEach-Object {
        if ($_.StartsWith("<Assembly: AssemblyFileVersion")) {
            $verStart = $_.IndexOf("(")
            $verEnd = $_.IndexOf(")", $verStart)
            $origVersion = $_.SubString($verStart + 2, $verEnd-$verStart - 3)
            
            $segments = $origVersion.Split(".")
            
            # Default values for each segment.
            $v1 = "1"
            $v2 = "0"
            $v3 = "0"
            $v4 = "0"
            
            # Assign them based on what was found.
            if ($segments.Length -gt 0) { $v1 = $segments[0] }
            if ($segments.Length -gt 1) { $v2 = $segments[1] } 
            if ($segments.Length -gt 2) { $v3 = $segments[2] } 
            if ($segments.Length -gt 3) { $v4 = $segments[3] } 
            
            $v1 = Get-VersionValue $v1 $MajorVer
            $v2 = Get-VersionValue $v2 $MinorVer
            $v3 = Get-VersionValue $v3 $BuildVer
            $v4 = Get-VersionValue $v4 $RevVer
            
            if (-not $v1) { throw "Major version CANNOT be blank!" }
            if (-not $v2) { throw "Minor version CANNOT be blank!" }
            
            $newVersion = "$v1.$v2"
            
            if ($v3) {
                $newVersion = "$newVersion.$v3"
                
                if ($v4) {
                    $newVersion = "$newVersion.$v4"
                }
            }

            Write-Host "Setting AssemblyVersion to $newVersion"
            return $_.Replace($origVersion, $newVersion)
        }
        else {
            return $_
        }
    }
    
    $newFile | Set-Content $PathToFile -Encoding "UTF8"
}
$major = $env:major_revision
$minor = $env:minor_revision 
$build = [int32](((Get-Date).Year - 2000) * 366) + (Get-Date).DayOfYear
#build = [string]((Get-Date).Day)+[string]((Get-Date).Month)+[string]((Get-Date).Year)
$rev=$env:Build_Id
#$rev=[int32](((get-date)-(Get-Date).Date).TotalSeconds / 2)

[string]$assemblyInfoFilePath = "$env:path"

#$VersionNumber = 'DLLVersionNumber = ' + $env:Major_Revision + '.' + $env:Minor_Revision + '.' + $build + '.' + $rev
#$VersionNumber | Add-Content $env:workspace\$env:Deployment_Details_File.Properties
Set-AssemblyFileVersion $assemblyInfoFilePath $major $minor $build $rev
"********************************************************************************************" 
"Major Version:" +$env:major_revision
"Minor Version: "+$env:minor_revision
"Build Number:"+$env:Build_Id
"Current Version:"+ $major+"."+$minor+"."+$build+"."+$rev
"********************************************************************************************"
" "
" "
"**************************Version Updated********************************"


