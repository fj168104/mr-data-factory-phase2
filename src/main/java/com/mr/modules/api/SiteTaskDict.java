package com.mr.modules.api;

/**
 * Created by feng on 18-3-16
 * SpringBoot环境下使用注解方式实例化Task类,不需要反射实例化,暂时不用到此类
 */
@Deprecated
public enum SiteTaskDict {
	DEMO("demo", "com.mr.modules.api.site.instance.DemoSiteTask"),
	SITE1("site1", "com.mr.modules.api.site.instance.SiteTaskImpl_1"),
	SITE2("site2", "com.mr.modules.api.site.instance.SiteTaskImpl_2"),
	SITE3("site3", "com.mr.modules.api.site.instance.SiteTaskImpl_3"),
	SITE4("site4", "com.mr.modules.api.site.instance.SiteTaskImpl_4"),
	SITE5("site5", "com.mr.modules.api.site.instance.SiteTaskImpl_5"),
	SITE6("site6", "com.mr.modules.api.site.instance.SiteTaskImpl_6"),
	SITE7("site7", "com.mr.modules.api.site.instance.SiteTaskImpl_7"),
	SITE8("site8", "com.mr.modules.api.site.instance.SiteTaskImpl_8"),
	SITE9("site9", "com.mr.modules.api.site.instance.SiteTaskImpl_9"),
	SITE10("site10", "com.mr.modules.api.site.instance.SiteTaskImpl_10");

	// 成员变量

	private String index;
	private String name;

	// 构造方法
	private SiteTaskDict(String index, String name) {
		this.name = name;
		this.index = index;
	}
	// 普通方法
	public static String getName(String index) {
		for (SiteTaskDict s : SiteTaskDict.values()) {
			if (s.getIndex().equals(index)) {
				return s.name;
			}
		}
		return null;
	}
	// get set 方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}


	public static void main(String[] s){
		System.out.println(SiteTaskDict.getName("demo"));
	}
}