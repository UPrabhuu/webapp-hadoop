package com.hwx.karthik.web.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.phoenix.schema.TableNotFoundException;
import org.apache.phoenix.util.PropertiesUtil;

import com.hwx.connection.ConfigurationManager;
import com.hwx.connection.Constant;
import com.opensymphony.xwork2.ActionContext;

public class PhoenixAction {

	// Return success if "datatable" is able to scan.
	public String execute() throws Exception {

		ResultSet rset = null;
		Connection con = null;
		try {
			// Action name from user request => phoenix
			String connectTo = ActionContext.getContext().getName();
			//Creating conf object and passing explicitly to phoenix
			Configuration conf = new ConfigurationManager(connectTo).getConf();
			Properties props=new Properties();
			Properties clusterProperties = PropertiesUtil.extractProperties(props, conf);
			
			// Loading Phoenix driver
			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
			
			// Connecting to phoenix.
			con = DriverManager.getConnection("jdbc:phoenix:" + ConfigurationManager.PHXCLUSTER_ZOOKEEPER_URL + ":"
					+ ConfigurationManager.PHXCLUSTER_HBASE_PRINCPAL + ":"
					+ ConfigurationManager.PHXCLUSTER_HBASE_KEYTAB,clusterProperties);
			
			//Phoenix statement
			PreparedStatement statement = con.prepareStatement("select * from "+Constant.tablename);
			rset = statement.executeQuery();
			statement.close();
			con.close();
		} catch(TableNotFoundException tnf){
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "oops";
		}
		return "failure";
	}
}
