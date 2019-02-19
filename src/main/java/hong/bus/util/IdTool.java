package hong.bus.util;

import java.util.Calendar;
import java.util.Random;

import hong.bus.common.Const;
import hong.bus.common.ServerResponse;

public class IdTool {
	private static final String [] _AtoZ = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"
			,"Q","R","S","T","U","V","W","X","Y","Z"};
	private static final String [] _0toZ = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"
			,"Q","R","S","T","U","V","W","X","Y","Z"};
	
	//生成纯英数组的随机索引
	public static int ReturnEngRandom()
	{
		Random rand = new Random();
		int randNum = rand.nextInt(26);
		return randNum;
	}
	
	//生成英数混合数组的随机索引
	public static int ReturnIntEngRandom()
	{
		Random rand = new Random();
		int randNum = rand.nextInt(36);
		return randNum;
	}
	
	//生成用户id的方法
	public static String createUserId(String prefix) {
		String uid = System.currentTimeMillis() + prefix;
		return uid;
	} 
	
	//按照长度生成数英混合的字符串  length为长度
	public static String getIdStringByNum(int length) {
		if(length == 0)
			return null;
		StringBuffer sb = new StringBuffer();	//采用同步的StringBuffer
		for(int i = 0; i < length; i++) {
			sb.append(_0toZ[ReturnIntEngRandom()]);
		}
		return sb.toString();
	}
	
	
}
