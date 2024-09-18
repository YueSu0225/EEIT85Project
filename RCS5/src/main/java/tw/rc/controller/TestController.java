package tw.rc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import tw.rc.model.MyTest;
import tw.rc.model.MyTestResponse;
import tw.rc.service.TestService;

@RequestMapping("/test")
@RestController
public class TestController {

	@GetMapping("/01")
	public MyTest test01() {
		MyTest myTest = new MyTest();
		myTest.setId(1L);
		myTest.setName("Brad");
		myTest.addFriend("Peter").addFriend("Tony").addFriend("Amy");
		
		return myTest;
	}
	
	@PostMapping("/02")
	public MyTest test02(@RequestBody MyTest mytest, HttpSession httpSession) {
		System.out.println(mytest.getId() + ":" + mytest.getName());
		mytest.addFriend("Peter").addFriend("Tony").addFriend("Amy");
		
		httpSession.setAttribute("myTest", mytest);
		
		return mytest;
	}
	
	@PostMapping("/03")//檢查session動作
	public MyTestResponse test03(HttpSession httpSession) {
		MyTestResponse myTestResponse = new MyTestResponse();
		
		if (httpSession.getAttribute("myTest") != null) {
			MyTest myTest = (MyTest)httpSession.getAttribute("myTest");
			myTestResponse.setCode(0);
			myTestResponse.setMesg("success");
			myTestResponse.setMyTest(myTest);
			
		}else {
			myTestResponse.setCode(-1);
			myTestResponse.setMesg("no session");

		}
		
		
		return myTestResponse;
	}
	
	@PostMapping("/04")//檢查session動作
	public MyTestResponse test04(HttpSession httpSession) {
		httpSession.invalidate();
		MyTestResponse myTestResponse = new MyTestResponse();
		myTestResponse.setCode(0);
		myTestResponse.setMesg("logout");
		return myTestResponse ;
	}
	
	@Autowired
	private TestService testService;
	
	@PostMapping("/05")// 上傳圖檔
	public ResponseEntity<String> test05(@RequestParam("uploadFile") MultipartFile uploadFile,
			@RequestParam Long id,
			@RequestParam String name) {
		System.out.println(uploadFile.getSize());
		System.out.println(id);
		System.out.println(name);
		return null;
		
//		try {
//			testService.uploadFile(uploadFile);
//			return ResponseEntity.status(HttpStatus.OK).body("upload success");
//		} catch (IOException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("upload failure");
//
//		}

	}
	
}
