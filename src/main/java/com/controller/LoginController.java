package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.OtpDao;
import com.dao.UserDao;
import com.service.MailService;
import com.service.OtpService;

@CrossOrigin(allowedHeaders = "*")
@RestController
public class LoginController {
	@Autowired
	UserDao userDao;
	@Autowired
	OtpService otpservice;
	@Autowired
	OtpDao otpDao;
	@Autowired
	MailService mailService;

	@GetMapping("resetpassword/{email}")
	public ResponseBean<UserBean> sendOtpForResetPassword(@PathVariable("email") String email, UserBean userBean) {
		System.out.println("Reset Called");
		userBean = userDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (userBean == null) {

			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);

		} else {
			String otp = otpservice.generateOtp();
			userBean.setOtp(otp);
			otpDao.updateOtp(email, otp);
			mailService.sendOtpForForgetPassword(userBean);

			responseBean.setMsg("Please Check Email for OTP");
			responseBean.setStatus(200);

		}

		return responseBean;
	}

	@PostMapping("/authenticate")
	public ResponseBean<UserBean> authenticate(@RequestBody LoginBean loginBean) {
		UserBean loBean = null;
		//System.out.println("Email : " + loginBean.getEmail());
		//System.out.println("Passowrd : " + loginBean.getPassword());
		loBean = userDao.login(loginBean.getEmail(), loginBean.getPassword());
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		if (loBean != null) {

			responseBean.setData(loBean);
			responseBean.setMsg("Login Successfully...");
			responseBean.setStatus(201);
		} else {
			responseBean.setMsg("Invalid Credentials...");
			responseBean.setStatus(200);
		}

		return responseBean;
	}

	@GetMapping("setnewpassword/{email}/{password}/{otp}")
	public ResponseBean<UserBean> setNewPasswordUsingOtp(@PathVariable("email") String email,
			@PathVariable("password") String password,@PathVariable("otp") String otp) {

		UserBean dbUser = userDao.getUserByEmail(email);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (dbUser == null) {

			responseBean.setMsg("User not found");
			responseBean.setStatus(201);

		} else {
			dbUser.setPassword(password);
			if (dbUser.getOtp().equals(otp)) {
				otpDao.updateOtp(email, "");
				userDao.updatePassword(dbUser);
				mailService.sendMailForPasswordUpdate(dbUser);
				responseBean.setMsg("Password Update.");
				responseBean.setStatus(200);

			} else {
				responseBean.setMsg("Invalid Otp....");
				responseBean.setStatus(201);

			}

		}

		return responseBean;
	}
}
