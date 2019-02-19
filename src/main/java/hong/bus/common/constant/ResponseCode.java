/**
 * 服务器返回值常量类
 */
package hong.bus.common.constant;

/**
 * @author Abbey
 *
 */
public enum ResponseCode {
	SUCCESS(0, "SUCCESS"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(10, "NEED_LOGIN"),
	ILLEGAL_ARGUMENT(2, "ILLLEGAL_ARGUEMENT"),
	WARNING(9, "WARING"),
	CHECK_AGAIN(3,"CHECK_AGAIN");
	
	/**
	 * 错误代码
	 */
	private final int code;
	/**
	 * 错误描述
	 */
	private final String description;
	
	ResponseCode(int code, String desc){
		this.code = code;
		this.description = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
