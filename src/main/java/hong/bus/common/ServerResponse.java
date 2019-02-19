/**
 * 服务器返回响应对象类
 * 经过序列化返回信息到前端
 */
package hong.bus.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import hong.bus.common.constant.ResponseCode;

/**
 * @author Abbey
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)	//保证序列化成Json出现空值时key也会消失
public class ServerResponse<T> implements Serializable {
	private int status;
	private String msg;
	private T data;
	
	private ServerResponse(int status) {
		this.status = status;
	}
	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	private ServerResponse(String msg, int status) {
		this.status = status;
		this.msg = msg;
	}
	@JsonIgnore	//使以下方法不在Json序列化当中
	public boolean isSuccess() {
		return this.status == 0;
	}
	
	public int getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
	//成功响应的对象获取途径--------开始
	public static <T> ServerResponse<T> createBySuccess(){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T> ServerResponse<T> createBySuccessMessage(String msg){
		return new ServerResponse<T>(msg,ResponseCode.SUCCESS.getCode());
	}
	
	public static <T> ServerResponse<T> createBySuccess(T data){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
	}
	
	public static <T> ServerResponse<T> createBySuccess(String msg, T data){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}
	//成功响应的对象获取途径--------结束
	
	//失败响应的对象获取途径--------开始
	public static <T> ServerResponse<T> createByError(){
		return new ServerResponse<T>(ResponseCode.ERROR.getCode());
	}
	
	public static <T> ServerResponse<T> createByErrorMessage(String msg){
		return new ServerResponse<T>(msg,ResponseCode.ERROR.getCode());
	}
	
	public static <T> ServerResponse<T> createByError(T data){
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(),data);
	}
	
	public static <T> ServerResponse<T> createByError(String msg, T data){
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg, data);
	}
	//失败响应的对象获取途径--------结束
	
	//其他状态响应的对象获取途径--------开始
	public static <T> ServerResponse<T> createResponse(int code){
		return new ServerResponse<T>(code);
	}
	
	public static <T> ServerResponse<T> createResponse(String msg, int code){
		return new ServerResponse<T>(msg,code);
	}
	
	public static <T> ServerResponse<T> createResponse(int code, T data){
		return new ServerResponse<T>(code,data);
	}
	
	public static <T> ServerResponse<T> createResponse(int code, String msg, T data){
		return new ServerResponse<T>(code, msg, data);
	}
	//其他状态响应的对象获取途径--------结束
}
