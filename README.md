This project is for internal use
It contains classes shared between Loaders and API.

##Deployment in dev
* Commit on develop (jenkins will automatically release a new snapshot version)

##Release a new version for the prod
* `nxs-fire-and-prepare-next-release.sh ${version-number}` (jenkins will merge develop into master and publish the release)
* for example `nxs-fire-and-prepare-next-release.sh 0.3.2`
