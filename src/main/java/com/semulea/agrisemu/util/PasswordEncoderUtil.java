package com.semulea.agrisemu.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

	public static void main(String[] args) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String rawPassword = "123456"; // A senha em texto simples que você deseja codificar
	        String encodedPassword = encoder.encode(rawPassword);
	        System.out.println("Senha codificada: " + encodedPassword);

	}

}
