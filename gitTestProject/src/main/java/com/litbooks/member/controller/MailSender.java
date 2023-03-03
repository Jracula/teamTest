package com.litbooks.member.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {


	public String emailCheck2(String email) {
		boolean result = false;
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com"); // 메일 보내는 주체가 gmail (stmp:메일 포로토콜)
		prop.put("mail.smtp.port", 587); // 587포트 사용
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", true); // 암호화 통신
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // TLS버전 설정
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // 신뢰할수 있는 주소임을 알려줌

		// 인증정보 설정(로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("hsb050505", "mctrduemkcmyhwlb");
				return pa;
			}
		});
		
		//이메일 작성해서 전송하는 객체
		MimeMessage msg = new MimeMessage(session);
		
		//★★★랜덤코드 생성
		//영어 소문자, 영어 대문자, 숫자 8자리
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<8;i++) {
			//0 ~ 9 : r.nextInt(10)  ->  0부터 매개변수 갯수 중 랜덤으로 1개 나옴
			//A ~ Z : (chat)(r.nextInt(26)+65);
			//a ~ z : (chat)(r.nextInt(26)+97);
			//0,1,2
			int flag = r.nextInt(3); 
			if(flag == 0) {
				//0~9
				int randomMember = r.nextInt(10);
				sb.append(randomMember);
			}else if(flag == 1) {
				//A-Z
				char randomChar = (char)(r.nextInt(25)+65);
				sb.append(randomChar);	
			}else if(flag == 2){
				//a-z
				char randomChar = (char)(r.nextInt(25)+97);
				sb.append(randomChar);	
			}
		}

		//이메일 작성
		try {
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress("hsb050505@gmail.com","LITBOOKS"));
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject("LITBOOKS 가입 인증메일입니다", "UTF-8");
			msg.setContent("<h1>안녕하세요, LITBOOKS 입니다.</h1>"+"<h3>인증번호는[<span style='color:red;'>"+sb.toString()+"</span>]입니다</h3>"
					, "text/html;charset=utf-8");
			Transport.send(msg);
			result = true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result) {
			return sb.toString();
		}else {
			return null;
		}

	}

	
}
