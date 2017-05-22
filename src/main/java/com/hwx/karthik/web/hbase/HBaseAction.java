package com.hwx.karthik.web.hbase;

import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.hwx.connection.ConfigurationManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HBaseAction extends ActionSupport {

	public String execute() throws Exception {

		try {
			String connectTo = ActionContext.getContext().getName();
			HBaseAdmin admin = new HBaseAdmin(new ConfigurationManager(connectTo).getConf());
			if (admin.isMasterRunning()) {
				return "success";
			}
			return "failure";
		} catch (Exception e) {
			e.printStackTrace();
			return "oops";
		}
	}
}
