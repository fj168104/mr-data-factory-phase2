package com.mr.modules.api;

/**
 * Created by feng on 18-3-17
 */
public enum TaskStatus {

	CALL_SUCCESS(0, "success"),
	CALL_FAIL(1, "failure"),
	CALL_ING(2, "executing");

	// 成员变量

	public int index;
	public String name;

	// 构造方法
	private TaskStatus(int index, String name) {
		this.index = index;
		this.name = name;
	}
	// 普通方法
	public static String getName(int index) {
		for (TaskStatus s : TaskStatus.values()) {
			if (s.getIndex() == index) {
				return s.name;
			}
		}
		return null;
	}

	// 普通方法
	public static int getIndex(String name) {
		for (TaskStatus s : TaskStatus.values()) {
			if (s.getName().equals(name)) {
				return s.index;
			}
		}
		return 1;
	}

	// get set 方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}


	public static void main(String[] s){
		System.out.println(TaskStatus.getName(1));
	}
}
