package com.cursosufcg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmail {

	@Autowired
	private JavaMailSender mailSender;
	
	public String EnviarEmail(String emailDestinatario) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Bem-vindo ao sistema de Cursos da UFCG!");
        message.setTo(emailDestinatario);

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
	}
	
}
