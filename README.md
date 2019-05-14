# HDX-Search-Logs

This microservice aggregates search logs. It is scala based, be sure to have
sbt and scala installed.

## Artifactory:

Be sure to set up your jfrog accounts, e.g. in `~/.sbt/repositories` you
should have something along the lines of:

```text
[repositories]
  local
  hdx-ontology-mvn-dev-snapshot-local: https://hdx.jfrog.io/hdx/hdx-ontology-mvn-dev-snapshot-local
  hdx-ontology-mvn-dev-local: https://hdx.jfrog.io/hdx/hdx-ontology-mvn-dev-local
  libs-release: https://hdx.jfrog.io/hdx/libs-release
  libs-snapshot: https://hdx.jfrog.io/hdx/libs-snapshot 
```

You will also need to store your credentials in `~/.sbt/1.0/credentials.sbt`:

```text
credentials += Credentials("Artifactory Realm", "hdx.jfrog.io", <user-name>, <password>)
```

## Building/Running:

First, launch sbt with `sbt`, then in the sbt shell do:

```sbtshell
reload
update
compile
```

To run it execute `run` (from the sbt shell).