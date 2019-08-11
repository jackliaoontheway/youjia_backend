package com.ryit.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryit.utils.VerifyCode;

@Controller
@RequestMapping("pages/admin")
public class VerifyCodeController {
    
	/**
     * 获得验证码
     * @throws IOException
     * */
	@RequestMapping("/verifyCode")
	public @ResponseBody void verify(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		VerifyCode vc = new VerifyCode();
		VerifyCode.output(vc.getImage(), response.getOutputStream());
		request.getSession().setAttribute("verify",vc.getText());
	}


}
