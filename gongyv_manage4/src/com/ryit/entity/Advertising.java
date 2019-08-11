package com.ryit.entity;

import java.util.Date;

//广告管理
public class Advertising extends AdstractEntity{
	
	private Integer id;//广告编号
	private String pic_num;//图片数量
	private String pic_url;//图片路径
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPic_num() {
		return pic_num;
	}
	public void setPic_num(String pic_num) {
		this.pic_num = pic_num;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pic_num == null) ? 0 : pic_num.hashCode());
		result = prime * result + ((pic_url == null) ? 0 : pic_url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advertising other = (Advertising) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pic_num == null) {
			if (other.pic_num != null)
				return false;
		} else if (!pic_num.equals(other.pic_num))
			return false;
		if (pic_url == null) {
			if (other.pic_url != null)
				return false;
		} else if (!pic_url.equals(other.pic_url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Advertising [id=" + id + ", pic_num=" + pic_num + ", pic_url=" + pic_url + "]";
	}
	public Advertising(String created_user, Date created_datetime, String updated_user, Date updated_datetime,
			Integer id, String pic_num, String pic_url) {
		super(created_user, created_datetime, updated_user, updated_datetime);
		this.id = id;
		this.pic_num = pic_num;
		this.pic_url = pic_url;
	}
	public Advertising() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
