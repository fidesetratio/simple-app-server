package com.app.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.app.auth.AppAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

public class JWTUtils {

	
	
	public static String createJWTToken(String user, List<SimpleGrantedAuthority> grantedRoles, HttpServletRequest request,String secretKey,Long timeout) {
		 	LocalDate now = LocalDate.now();
		 	ZoneId defaultZoneId = ZoneId.systemDefault();
	        Claims clms = Jwts.claims().setSubject(user);
	        if(request != null)
	        clms.put("IP", RequestUtility.getIpAddress(request));
	        if(grantedRoles != null)
	        clms.put("scopes",grantedRoles);
	        Date createdOn = Date.from(Instant.now());
	        Date timeoutOn = Date.from(createdOn.toInstant().plusMillis(timeout));
	        Key key = setKey(secretKey);
	        if(key != null) {
	        return Jwts.builder()
	                .setId(UUID.randomUUID().toString())
	                .setClaims(clms)
	                .setSubject(user)
	                .setIssuer(user)
	                .setIssuedAt(createdOn)
	                .setExpiration(timeoutOn)
	                .signWith(key)
	                .compact();

	        }
	        return null;
	}


	public static Key setKey(String secretKey) {

		try {
			Key key = Keys.hmacShaKeyFor(secretKey.getBytes("UTF-8"));
			return key;
		} catch (WeakKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	
	
	}
	
	public static Date getExpirationDateFromToken(String token,String secretKey) {
        return getClaimFromToken(token, Claims::getExpiration,secretKey);
    }

	

	public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, String secretKey) {
        final Claims claims = parseJWTToken(token,secretKey);
        return claimsResolver.apply(claims);
    }
	
	
	public static Claims parseJWTToken(String token, String secretKey) {
		Key key = setKey(secretKey);
		
		if(key != null) {
		 Jws<Claims> headerClaimsJwt =
	                Jwts.parserBuilder()
	                        .setSigningKey(key)
	                        .build()
	                        .parseClaimsJws(token);
	        return headerClaimsJwt.getBody();
		}else {
			return null;
		}
	}

	
	public static Boolean isTokenExpired(String jwtToken, String secretKey) {
		Boolean ret = Boolean.TRUE;
		try {
			Date expirationDate = getExpirationDateFromToken(jwtToken, secretKey);
			return expirationDate.before(new Date());
		}catch(ExpiredJwtException e) {
				e.printStackTrace();
		}
		return ret;
	}

	
	public static Boolean isValid(String jwtToken, String secretKey, AppAuthentication authentication) {
			if(!isTokenExpired(jwtToken, secretKey) && !authentication.isPartOfBlackLists() && authentication.isValidUserName()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
	}
	
	public static void main(String args[]) {
		String secretKey = "YdTbooqDbh5Q9fI3H9df2SCOBfKXPn1GS/5/NPKwPA78r5FrMLhgtpP81vKRekkX";
		String jwtToken = JWTUtils.createJWTToken("patar",null,null,secretKey,10000L);
		System.out.println("Is Token Expired:"+JWTUtils.isTokenExpired(jwtToken, secretKey));
		
	}
	
	
}
