package com.ams.presentacion.security;

public class SecurityConstants {
	// Configuración del token

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	//Clave de acceso
	public static final String SIGNING_KEY = "admin";
	
	//Tiempo de expiracion del token
	public static final long TOKEN_EXPIRATION_TIME = 604800000; // 7 dias
	
}
