package com.amap.driverdemo.common;

/**
 * Package : com.amap.driverdemo.common
 * Author : xudong.tang
 * Date   : 18/4/19
 */

public class Constants {

    /**
     * bundle中存放OrderInfo对应的key
     */
    public static final String BUNDLE_KEY_ORDERINFO = "orderInfo";
    public static final String BUNDLE_KEY_PRICE = "price";
    public static final String BUNDLE_KEY_DISTANCE = "distance";
    public static final String BUNDLE_KEY_TIME= "time";

    /**
     * 乘客基本信息描述
     */
    public static final String USERINFO_ORDER_COMPLETE_RATIO_FORMAT = "打车%d次 订单完成度%d%%";


    /**
     * 乘客电话号码格式化
     */
    public static final String USERINFO_PHONE_NUMBER_FORMAT_REGEX = "(\\d{3})\\d{4}(\\d{4})";
    public static final String USERINFO_PHONE_NUMBER_FORMAT_REPLACEMENT = "$1****$2";

    /**
     * 价钱
     */
    public static final String ORDERINFO_PRICE_FORMAT = "%.2f元";

    /**
     * 行驶距离格式化
     */
    public static final String ORDERINFO_DISTANCE_AND_TIME_FORMAT = "行驶距离%.2f公里   行驶时间%s";


    /**
     * 服务流程页面提示
     */
    public static final String SERVICE_TIP_PICKINGUP_FORMAT = "距离乘客%.2f公里，预计%s后到达";
    public static final String SERVICE_TIP_WAITING_FORMAT = "等待乘客上车";
    public static final String SERVICE_TIP_INSERVICE_FORMAT = "已行驶%.2f公里，行驶时长%s";

}
