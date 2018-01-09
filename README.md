# karaf-example

The project contains 3 modules:

- example-runtime - karaf assembly that contains configured h2 datasource
- example-jar - bundle that has a dependency on this datasource
- example-feature - feature that includes example-jar bundle.

If I deploy example-jar directly, it works.

If I try to deploy example-feature, I get an exception:

```
2018-01-09 12:45:10,356 | WARN  | -SNAPSHOT/deploy | KarServiceImpl                   | 59 - org.apache.karaf.kar.core - 4.1.4 | Unable to install Kar feature example-feature/0.1.0.SNAPSHOT
org.osgi.service.resolver.ResolutionException: Unable to resolve root: missing requirement [root] osgi.identity; osgi.identity=example-feature; type=karaf.feature; version="[0.1.0.SNAPSHOT,0.1.0.SNAPSHOT]"; filter:="(&(osgi.identity=example-feature)(type=karaf.feature)(version>=0.1.0.SNAPSHOT)(version<=0.1.0.SNAPSHOT))" [caused by: Unable to resolve example-feature/0.1.0.SNAPSHOT: missing requirement [example-feature/0.1.0.SNAPSHOT] osgi.identity; osgi.identity=example-jar; type=osgi.bundle; version="[0.1.0.SNAPSHOT,0.1.0.SNAPSHOT]"; resolution:=mandatory [caused by: Unable to resolve example-jar/0.1.0.SNAPSHOT: missing requirement [example-jar/0.1.0.SNAPSHOT] osgi.service; effective:=active; filter:="(&(objectClass=javax.sql.DataSource)(osgi.jndi.service.name=testds-h2))"]]
	at org.apache.felix.resolver.ResolutionError.toException(ResolutionError.java:42) ~[?:?]
	at org.apache.felix.resolver.ResolverImpl.doResolve(ResolverImpl.java:391) ~[?:?]
	at org.apache.felix.resolver.ResolverImpl.resolve(ResolverImpl.java:377) ~[?:?]
	at org.apache.felix.resolver.ResolverImpl.resolve(ResolverImpl.java:349) ~[?:?]
	at org.apache.karaf.features.internal.region.SubsystemResolver.resolve(SubsystemResolver.java:218) ~[?:?]
	at org.apache.karaf.features.internal.service.Deployer.deploy(Deployer.java:291) ~[?:?]
	at org.apache.karaf.features.internal.service.FeaturesServiceImpl.doProvision(FeaturesServiceImpl.java:1248) ~[?:?]
	at org.apache.karaf.features.internal.service.FeaturesServiceImpl.lambda$doProvisionInThread$1(FeaturesServiceImpl.java:1147) ~[?:?]
	at java.util.concurrent.FutureTask.run(FutureTask.java:266) [?:?]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [?:?]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [?:?]
	at java.lang.Thread.run(Thread.java:748) [?:?]
```

Output of service:list javax.sql.DataSource command:

```
[javax.sql.DataSource]
----------------------
 databaseName = test
 dataSourceName = testds-h2
 felix.fileinstall.filename = file:/Users/e3ckuo/Temp/example/example-runtime-0.1.0-SNAPSHOT/etc/org.ops4j.datasource-test.cfg
 osgi.jdbc.driver.name = H2
 osgi.jndi.service.name = testds-h2
 password =
 service.bundleid = 13
 service.factoryPid = org.ops4j.datasource
 service.id = 128
 service.pid = org.ops4j.datasource.63eb6fb8-e203-4a9d-bc3d-6020107197c2
 service.scope = singleton
 user = sa
Provided by :
 OPS4J Pax JDBC Config (13)
 ```
It was working in karaf 3.0.6
