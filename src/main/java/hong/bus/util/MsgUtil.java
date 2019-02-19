/**
 * 
 */
package hong.bus.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;
import hong.bus.common.VerifyCache;

/**
 * @author Abbey
 *
 */
public class MsgUtil {
	private static final String smsTextStart = "您现在申请登录，验证码为：";
	private static final String otherTextStart = "您的验证码为：";
	private static final String smsTextEnd = "，工作人员不会向您索取，请勿泄露他人!";
	private static Map<String, Integer> checkLoginMap = new HashMap<String, Integer>();
	
	public static String sendMessage(String mobileNum, String prefix,String verifyNum) throws UnsupportedEncodingException, IOException{
		HttpClient client = new HttpClient(); 
		String Uid = PropertiesUtil.getProperty("Uid");
		String Key = PropertiesUtil.getProperty("Key");
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");   
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码  
        StringBuilder smsText = new StringBuilder();
        if(Const.LOGIN_PREFIX.equals(prefix))
        	smsText.append(smsTextStart);
        else
        	smsText.append(otherTextStart);
        smsText.append(verifyNum);	//生成的验证码
        smsText.append(smsTextEnd);
        NameValuePair[] data ={   
        new NameValuePair("Uid", Uid),  
        new NameValuePair("Key", Key),  
        new NameValuePair("smsMob",mobileNum),
        
        new NameValuePair("smsText",smsText.toString())
        };  
        post.setRequestBody(data);  
  
        try {
			client.executeMethod(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:"+statusCode);  
  
        for(Header h : headers){  
           System.out.println(h.toString());  
        }  
        String result = null;
		try {
			result = new String(post.getResponseBodyAsString().getBytes("utf8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        System.out.println(result);  
  
        post.releaseConnection();
        return result;
	}
	

	/**
	 * 生成验证码
	 * @return 一个随机的六位验证码
	 */
	public static String radomVerifyNum() {

		Random random = new Random();
		StringBuilder varifyNum = new StringBuilder();
		for (int i=0;i<6;i++)
		{
			varifyNum.append(random.nextInt(10));
		}
		if(null == varifyNum) 
			return null;
		else
			return varifyNum.toString();
	}
	
	public static int checkVerify(String cacheKey, String verifyNum) {
		String serverVerifyNum = VerifyCache.getValueByKey(cacheKey);//缓存中的验证码
		if((null != checkLoginMap.get(cacheKey) && checkLoginMap.get(cacheKey) >= 5) || 
				(null != VerifyCache.getValueByKey(cacheKey) && VerifyCache.getValueByKey(cacheKey) == Const.ERROR_LOCK))//如果尝试登录错误次数过多，则返回9
		{
			VerifyCache.setKeyValue(cacheKey, Const.ERROR_LOCK);
			checkLoginMap.remove(cacheKey);
			return 9;//尝试次数过多
		}
		if(serverVerifyNum == null) {
			
			return 1;//尚未获取验证码
			
		}
		if(!verifyNum.equals(serverVerifyNum))//如果验证码不匹配
		{
			if(checkLoginMap.containsKey(cacheKey))
			{
				checkLoginMap.put(cacheKey, checkLoginMap.get(cacheKey)+1);
			}
			else {
				checkLoginMap.put(cacheKey, 1);
			}
			return 2;//验证码不匹配
		}
		if(checkLoginMap.containsKey(cacheKey))
			checkLoginMap.remove(cacheKey);
		return 0;//成功
	}
	
	/**
	 * 验证码校验方法
	 * @param prefix	验证码缓存前缀
	 * @param mobileNum	手机号
	 * @param verifyNum	验证码
	 * @return
	 */
	public static <T> ServerResponse<T> verifyValid(String prefix, String mobileNum, String verifyNum){
		String cacheKey = prefix + mobileNum;//构造key, 获取缓存的登录验证码
		int isPass= MsgUtil.checkVerify(cacheKey, verifyNum);
		if(isPass == 9)
			return ServerResponse.createByErrorMessage("尝试次数过多！");
		if(isPass == 1) {
			return ServerResponse.createByErrorMessage("尚未获取验证码！");
		}
		if(isPass == 2)
			return ServerResponse.createByErrorMessage("验证码不匹配！");
		return ServerResponse.createBySuccess();
	}
}
