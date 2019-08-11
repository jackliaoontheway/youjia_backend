package com.ryit.utils;

public class Constants {
	public static final String ADMIN_USER = "adminUser";
    public static final String USER = "web_user";
    
	public static final String	MOBILE_NO = "mobile";										// 手机号码
	public static final String	MOBILE_VERIFY_CODE	= "mobile_verify";						// 手机验证码
	public static final String	MOBILE_VERIFY_CODE_SEND_TIME = "mobile_verify_send_tme";	// 手机验证码发送时间
	public static final int MAX_TIME = 180;													// session最长保留验证码时间(3分钟)
	public static final String SMS_MOBILE_AREA_CODE_CN_ELSE = "1";
	public static final String REGISTER_MOBILE_NO = "register_mobile";                     //注册手机号码
	public static final String REGISTER_MOBILE_VERIFY_CODE = "register_mobile_verify";     //注册手机验证码
	public static final String REGISTER_MOBILE_VERIFY_CODE_SEND_TIME = "register_mobile_verify_send_tme"; //注册手机验证码超期时间
	public static final String PWD_USER_ID="pwd_userId";
	
	public static final String  PROTAL_SMS = "Protal_yzm";


	//购买商主账户角色（总公司）
	public static final int BUYER_MAJOR_ROLE = 1;
	//购买商子账户角色（连锁店）
	public static final int BUYER_MINOR_ROLE = 2;
	//购买商主账户角色名（总公司）
	public static final String BUYER_MAJOR_ROLE_NAME = "ADMIN";
	//购买商子账户角色名
	public static final String BUYER_MINOR_ROLE_NAME = "SUB_ACCOUNT";


	public static final String CHINA_CODE = "A000086000";
	public static final String CHINA_NAME = "中国";

	//订单状态-已审核
	public static final int ORDER_STATUS_AUDITED = 2;
	public static final int ORDER_STATUS_CREATE = 1;
	public static final int ORDER_STATUS_INVALID = 0;
	public static final int ORDER_STATUS_REJECT = 3;

	//联系人类型，1购买商连锁店，2供应商
	public static final int CONTACTS_TYPE_BUYER = 1;
	public static final int CONTACTS_TYPE_SELLER = 2;


	public static final int COLD_PRODUCT = 3;

	//生成供应商code前缀(CSSP-SP-)
	public static final String SUPPLIER_CODE = "SP";

	//生成商品code前缀
	public static final String CARGOS_CODE = "CSSP-CARGO-";

	public static final String LOG4J_CATEGROY = "CSSP";

	public static final String RESULT_PAGE = "web/result";

	//订单类型(入库)
	public static final String ORDER_TYPE_IN = "1";

	//订单类型(出库)
	public static final String ORDER_TYPE_OUT = "2";

	//订单类型(冷运)
	public static final String ORDER_TYPE_COLD = "3";

	//订单类型(重货)
	public static final String ORDER_TYPE_HEAVY = "4";

	//订单类型编码(销退入库)
	public static final String ORDER_TYPE_CODE_RI = "RI";

	//订单类型编码(采购入库)
	public static final String ORDER_TYPE_CODE_PI = "PI";

	//订单类型编码(报溢入库)
	public static final String ORDER_TYPE_CODE_OI = "OI";

	//订单类型编码(销退出库)
	public static final String ORDER_TYPE_CODE_RO = "RO";

	//订单类型编码(采购出库)
	public static final String ORDER_TYPE_CODE_PO = "PO";

	//订单类型编码(报损出库)
	public static final String ORDER_TYPE_CODE_BO = "BO";

}

