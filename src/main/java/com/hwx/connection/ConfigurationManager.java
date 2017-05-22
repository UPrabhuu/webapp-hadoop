package com.hwx.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

public class ConfigurationManager {
	
	
	private static Properties props = null;
	private String conx = "";
	private Configuration conf = null;
	public static String PHXCLUSTER_ZOOKEEPER_URL="";
	public static String PHXCLUSTER_HBASE_KEYTAB="";
	public static String PHXCLUSTER_HBASE_PRINCPAL="";
	public static String PHXCLUSTER_KRB5_CONF="";

	static {
		props = new Properties();
		try {
			props.load(new FileInputStream(new File("/var/node.properties")));
			PHXCLUSTER_ZOOKEEPER_URL=props.getProperty("cluster.b.phoenix.zoo.url");
			PHXCLUSTER_HBASE_KEYTAB=props.getProperty("cluster.b.hbase.keytab");
			PHXCLUSTER_HBASE_PRINCPAL=props.getProperty("cluster.b.hbase.princpal");
			PHXCLUSTER_KRB5_CONF=props.getProperty("cluster.b.krb5.conf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConfigurationManager(String conx) {
		this.conx = conx;
	}

	public Configuration getConf() throws IOException {
		if (conx.equalsIgnoreCase("hbase")) {
			conf = new Configuration();
			conf.addResource(new Path(props.getProperty("cluster.a.hadoop.coresite")));
			conf.addResource(new Path(props.getProperty("cluster.a.hadoop.hdfssite")));
			conf.addResource(new Path(props.getProperty("cluster.a.hbase.hbasesite")));
			System.setProperty("java.security.krb5.conf", props.getProperty("cluster.a.krb5.conf"));
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(props.getProperty("cluster.a.hbase.princpal"),
					props.getProperty("cluster.a.hbase.keytab"));

			
		} else if (conx.equalsIgnoreCase("hadoop")) {
			conf = new Configuration();
			conf.addResource(new Path(props.getProperty("cluster.a.hadoop.coresite")));
			conf.addResource(new Path(props.getProperty("cluster.a.hadoop.hdfssite")));
			
			System.setProperty("java.security.krb5.conf", props.getProperty("cluster.a.krb5.conf"));
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(props.getProperty("cluster.a.hadoop.princpal"),
					props.getProperty("cluster.a.hadoop.keytab"));
			
			
		} else if (conx.equalsIgnoreCase("phoenix")) {
			conf = new Configuration();
			conf.addResource(new Path(props.getProperty("cluster.b.hadoop.coresite")));
			conf.addResource(new Path(props.getProperty("cluster.b.hadoop.hdfssite")));
			conf.addResource(new Path(props.getProperty("cluster.b.hbase.hbasesite")));
			System.setProperty("java.security.krb5.conf", PHXCLUSTER_KRB5_CONF);
		}
		return conf;
	}
}