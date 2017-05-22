<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hadoop WebApp</title>
</head>
<body>
	<form action="hbase">
		<input type="submit" value="Connect HBase -Cluster(A)" />
	</form>
	<form action="hadoop">
		<input type="submit" value="Connect HDFS -Cluster(A)" />
	</form>
	<form action="phoenix">
		<input type="submit" value="Connect Phoenix -Cluster(B)" />
	</form>
</body>
</html>