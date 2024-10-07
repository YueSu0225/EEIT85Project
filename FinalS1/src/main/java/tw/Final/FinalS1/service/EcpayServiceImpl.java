package tw.Final.FinalS1.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import tw.Final.FinalS1.dto.OrderResponse;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.UserRepository;

@Service
public class EcpayServiceImpl implements EcpayService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
	@Autowired
	private JavaMailSender javaMailSender;	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public String receiveECPayResponse(HttpServletRequest request) {
		
		// 收到回傳東西
		Map<String, String[]> parameterMap = request.getParameterMap();

		// 打印所有回傳
		parameterMap.forEach((key, value) -> {
			System.out.println(key + ": " + Arrays.toString(value));
		
		});
		// 創建回傳清單
	    StringBuilder responseString = new StringBuilder();
	    parameterMap.forEach((key, value) -> {
	        responseString.append(key).append(": ").append(Arrays.toString(value)).append("\n");
	    });
	    
	    //RtnCode: [1] 付款完成
	    
	    // 檢查付款是否成功
        String[] rtnCode = parameterMap.get("RtnCode");
        if (rtnCode != null && "1".equals(rtnCode[0])) { // 確認付款 是否成功
            String[] customField1 = parameterMap.get("CustomField1");
            String[] customField2 = parameterMap.get("CustomField2");
            Long userId = Long.parseLong(customField1[0]); // 解析userID
            Long cartId = Long.parseLong(customField2[0]); // 解析cartID
            String[] merchantTradeNo = parameterMap.get("MerchantTradeNo"); // 獲取MerchantTradeNo
            String[] totalAmount = parameterMap.get("TradeAmt"); // 獲取總金額 
            

            
            // 調用 createOrderFromCart 方法
            OrderModel order = orderService.createOrderFromCart(userId, cartId, merchantTradeNo[0]);

            // 將訂單實體轉換為 DTO
            OrderResponse orderResponse = orderService.convertToOrderResponse(order);
            
            UserModel userModel = userRepository.findById(userId)
            	    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            String userEmail = userModel.getEmail();
            
         // 商品名稱,價格,數量
            List<Map<String, Object>> itemDetails = orderResponse.getOrderItems().stream()
                    .map(orderItem -> {
                        Map<String, Object> itemDetail = new HashMap<>();
                        itemDetail.put("productName", orderItem.getProductName());
                        itemDetail.put("quantity", orderItem.getQuantity());
                        itemDetail.put("price", orderItem.getPrice());
                        return itemDetail;
                    }).collect(Collectors.toList());

            
            
         // 使用 Thymeleaf 生成内容
            Context context = new Context();
            context.setVariable("orderNumber", merchantTradeNo[0]);
            context.setVariable("totalAmount", totalAmount[0]);
            context.setVariable("itemNames", itemDetails);
            context.setVariable("orderStatus", "已付款"); // 或者从 orderResponse 中获取状态
            String emailContent = templateEngine.process("orderSuccess", context); 

            try {
                // 創建信件
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true); // true 表示支持附件
                helper.setTo(userEmail);
                helper.setSubject("訂單確認");
                helper.setText(emailContent, true); // true 表示邮件内容为 HTML

                // 發送信件
                javaMailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace(); // 打印错误信息
            }
            
            
            // 訂單成功信息
            System.out.println("orderResponse: " + orderResponse.toString());
            return "訂單已成功建立: " + orderResponse.toString();
        } else {
            // 處理未成功狀況
            return "付款未成功，請檢查付款狀態。";
        }


	}

}