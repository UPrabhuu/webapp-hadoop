# webapp-hadoop
Sample Web Application - Connecting to multi secure clusters (HADOOP/HDFS, HBase, Phoenix) via single webapp container

Note: webapp-hadoop/src/main/java/com/hwx/connection/ConfigurationManager.java

## Steps to Run:

#### 1) Download webapp to local

#### 2) Build this project

$mvn clean install

#### 3) Create the property file "/var/node.properties" in tomcat machine

#### 4) Add cluster/connection details to this.

example:

$ cat /var/node.properties

#Cluster(A) HBase and HDFS

cluster.a.hadoop.coresite=/etc/hadoop/conf/core-site.xml

cluster.a.hadoop.hdfssite=/etc/hadoop/conf/hdfs-site.xml

cluster.a.hbase.hbasesite=/etc/hbase/conf/hbase-site.xml

#Secure detail

cluster.a.krb5.conf=/etc/hadoop/conf/krb5.conf

cluster.a.hadoop.keytab=/etc/security/keytabs/hdfs.headless.keytab

cluster.a.hadoop.princpal=hdfs-kphdp24@KP24.COM

cluster.a.hbase.keytab=/etc/security/keytabs/hbase.headless.keytab

cluster.a.hbase.princpal=hbase-kphdp24@KP24.COM

#Cluster(B) PHOENIX

cluster.b.hadoop.coresite=/tmp/clusterb/core-site.xml

cluster.b.hadoop.hdfssite=/tmp/clusterb/hdfs-site.xml

cluster.b.hbase.hbasesite=/tmp/clusterb/hbase-site.xml

#Secure detail

cluster.b.krb5.conf=/tmp/clusterb/krb5.conf

cluster.b.phoenix.zoo.url=kphdp251.openstacklocal,kphdp252.openstacklocal,kphdp253.openstacklocal:2181:/hbase-secure

cluster.b.hbase.keytab=/tmp/clusterb/hbase.headless.keytab

cluster.b.hbase.princpal=hbase-kphdp25@KP25.COM

#### 5)Deploy generated war file under "tomcat-path/webapp/"

#### 6)http://tomcat-ip:port/webapp-hadoop/

7)Done!!
