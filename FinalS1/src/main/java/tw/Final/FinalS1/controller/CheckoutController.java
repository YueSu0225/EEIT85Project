package tw.Final.FinalS1.controller;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import jakarta.servlet.http.HttpSession;
import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.CartItemsRepository;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.service.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 跳轉
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final AllInOne allInOne;
    
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    
    // 使用 @Value 注入配置文件路徑
    public CheckoutController() {
        try {
            this.allInOne = new AllInOne("");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize AllInOne with config path: " +  e);
        }
    }
    private String generateEcpayNumber() {
        return "EEIT85" + System.currentTimeMillis();
    }

    @PostMapping("/create")
    @ResponseBody
    public String checkout(HttpSession session) {
    	
    	String userUUID = (String)session.getAttribute("userUUID");
    	UserModel user = userRepository.findByUuid(userUUID);
    	CartModel cart = cartRepository.findByUser(user);
        if (userUUID == null) {
            throw new RuntimeException("User not logged in.");
        }
        
        List<CartItemsModel> cartItems = cartItemsRepository.findByCartId(user.getId());
        
        int totalAmount = 0;
        StringBuilder itemNames = new StringBuilder();
        for( CartItemsModel item : cartItems) {
        	String productName = item.getProductVariant().getProduct().getName();
        	int itemPrice = item.getPrice();
        	int quantity =item.getQuantity();
        	       	
            int itemTotalPrice = itemPrice * quantity;
            totalAmount += itemTotalPrice;
        	
        	itemNames.append(productName).append(" * ").append(quantity).append(" =  $").append(itemPrice).append("#");
        }
     // 移除最後一個的"#"符號
        if (itemNames.length() > 0) {
            itemNames.setLength(itemNames.length() - 1);
        }
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String currentTime = now.format(formatter);
        System.out.println(itemNames.toString());
        AioCheckOutOneTime obj = new AioCheckOutOneTime();
        obj.setMerchantID("EEIT2024");
        obj.setMerchantTradeNo(generateEcpayNumber());
        obj.setMerchantTradeDate(currentTime);
        obj.setTotalAmount(String.valueOf(totalAmount));
        obj.setTradeDesc("EEIT專案");
        obj.setItemName(itemNames.toString());       
        obj.setReturnURL("https://23d9-218-32-102-243.ngrok-free.app/ecpayresponse/receiveECPayResponse");
        obj.setClientBackURL("http://localhost:8080/Home.html");
        obj.setNeedExtraPaidInfo("N");
        obj.setChooseSubPayment("ALL");
        
        // 設定自訂,將userID以及cartID放進去
        obj.setCustomField1(user.getId().toString()); 
        obj.setCustomField2(cart.getId().toString()); 

        // 產生金流頁面的表單 HTML
        String form = allInOne.aioCheckOut(obj, null);
        return form;
    }
}
