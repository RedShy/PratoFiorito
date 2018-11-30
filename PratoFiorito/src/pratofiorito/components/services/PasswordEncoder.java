package pratofiorito.components.services;

import java.security.MessageDigest;

public class PasswordEncoder {

	public String encode(String rawPassword) {
		String hashed = encrypt(rawPassword); 
		return hashed;
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return checkPassword(rawPassword, encodedPassword) != null;

	}

	private String encrypt(String password) {
		String hash = null;
		
		try {
			hash = byteArrayToHexString(computeHash(password));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash;
	}
	
	private String checkPassword(String newPassword, String savedPassword) {
		String hash;
		try {
			hash = byteArrayToHexString(computeHash(newPassword));

			if (hash.equals(savedPassword)) 
				return hash;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private byte[] computeHash(String x) throws Exception {
		MessageDigest d =null;
	    d = MessageDigest.getInstance("SHA-1");
	    d.reset();
	    d.update(x.getBytes());
	    return  d.digest();
	}

	private String byteArrayToHexString(byte[] b) {
		 
		StringBuffer sb = new StringBuffer(b.length * 2);
	    for (int i = 0; i < b.length; i++) {
	    	int v = b[i] & 0xff;
	    	if (v < 16)
	    		sb.append('0');
	    	 
	    	sb.append(Integer.toHexString(v));
	    }
	    return sb.toString().toUpperCase();
	}
	


}