package com.hwx.karthik.web.hdfs;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hwx.connection.ConfigurationManager;
import com.hwx.connection.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HdfsAction extends ActionSupport {

	public String execute() throws Exception {

		try {
			String connectTo = ActionContext.getContext().getName();
			FileSystem fs = FileSystem.get(new ConfigurationManager(connectTo).getConf());
			if (!fs.isDirectory(new Path("/" + Constant.tablename))) {
				return "success";
			}
			return "failure";
		} catch (Exception e) {
			e.printStackTrace();
			return "oops";
		}

	}
}
