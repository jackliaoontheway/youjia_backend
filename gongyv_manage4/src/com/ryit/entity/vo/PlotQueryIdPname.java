package com.ryit.entity.vo;

//查询小区编号和小区名称，此查询是用来给栋数查询小区名称的，是给栋数管理使用的
public class PlotQueryIdPname {

	private Integer pid;
	private String pname;
	
	public PlotQueryIdPname(){
		
	}
	
	public PlotQueryIdPname(Integer pid,String pname){
		this.pid = pid;
		this.pname = pname;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}


	public String toString() {
		return "PlotQueryIdPname [pid=" + pid + ", pname=" + pname + "]";
	}
	
	
}
