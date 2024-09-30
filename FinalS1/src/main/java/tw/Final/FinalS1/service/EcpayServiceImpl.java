package tw.Final.FinalS1.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EcpayServiceImpl implements EcpayService {

	@Override
	public String receiveECPayResponse(HttpServletRequest request) {
		
	    
		// 获取所有的回传参数
		Map<String, String[]> parameterMap = request.getParameterMap();

		// 打印所有的回传参数
		parameterMap.forEach((key, value) -> {
			System.out.println(key + ": " + Arrays.toString(value));
		
		});
		// 创建一个可读的返回字符串
	    StringBuilder responseString = new StringBuilder();
	    parameterMap.forEach((key, value) -> {
	        responseString.append(key).append(": ").append(Arrays.toString(value)).append("\n");
	    });
	    
	    //RtnCode: [1] 付款完成
	    
	    // 返回所有参数的字符串
	    return responseString.toString();

//		// 验证 CheckMacValue 来确保数据未被篡改
//		String receivedCheckMacValue = request.getParameter("CheckMacValue");
//		
//		Map<String, String> params = new HashMap<>();
//        if (parameterMap.isEmpty()) {
//            System.out.println("No parameters found in request.");
//        }
//		 // 存储有效的参数
//	    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//	        String key = entry.getKey();
//	        String[] value = entry.getValue();
//	        System.out.println("鍵: " + key + ", 值: " + Arrays.toString(value)); // 调试输出
//	        
//	        if (value != null && value.length > 0 && !value[0].isEmpty()) { // 确保值不为空
//	            params.put(key, value[0]);
//	            System.out.println("已存入: " + key + " -> " + value[0]); // 添加存入调试
//	        }else {
//	        System.out.println("鍵: " + key + " 為空");
//	        }
//	    }
//	    System.out.println("所有回傳參數: " + parameterMap);
////		// 排除不參與計算的参数
//		params.remove("CheckMacValue");
//		
//	    
//	    System.out.println("计算 CheckMacValue 使用的参数: " + params);
//	    

//		// 使用綠界提供的工具來驗證 CheckMacValue
//	    	String calculatedCheckMacValue = EcpayFunction.genCheckMacValue("5294y06JbISpM5x9", "v77hoKGq4kWxNNIS", params);
//		
//
//		
//		// 驗證 CheckMacValue
//		if (receivedCheckMacValue != null && receivedCheckMacValue.equals(calculatedCheckMacValue)) {
//			// CheckMacValue 驗證成功，處理其他邏輯
//			
//			System.out.println("收到的 CheckMacValue: " + receivedCheckMacValue);
//			System.out.println("計算的 CheckMacValue: " + calculatedCheckMacValue);
//
//			String rtnCode = request.getParameter("RtnCode");
//			if ("1".equals(rtnCode)) {
//				System.out.println("交易成功");
//				// 交易成功，更新訂單狀態
//				return "1|OK"; // 回應綠界告知接收成功
//			} else {
//				System.out.println("交易失敗");
//				// 交易失敗或其他狀況，記錄問題
//				return "0|Fail";
//			}
//		} else {
//			System.out.println("驗證失敗");
//			// CheckMacValue 驗證失敗，可能資料被竄改
//			return "0|CheckMacValueError";
//		
//		
//	    }
	}

}