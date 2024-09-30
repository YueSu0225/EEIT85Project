package tw.Final.FinalS1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import tw.Final.FinalS1.service.EcpayService;

@RestController
@RequestMapping("/ecpayresponse")
public class EcpayResponseController {
	
	@Autowired
	private EcpayService ecpayService;
	
	@PostMapping("/receiveECPayResponse")
	@ResponseBody
	public String receiveECPayResponse(HttpServletRequest request) {
		System.out.println("收到綠界回傳請求");
		return ecpayService.receiveECPayResponse(request);
	}

}
