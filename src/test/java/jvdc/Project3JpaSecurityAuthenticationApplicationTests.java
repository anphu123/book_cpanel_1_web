package jvdc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class Project3JpaSecurityAuthenticationApplicationTests {

	@Test
	void contextLoads() {

	}
	@Test
	public void testEncodePassword() throws NoSuchAlgorithmException {
		PasswordEncoder passwordEncoder = new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				String hashtext = null;
				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] messageDigest = md.digest(rawPassword.toString().getBytes());
					BigInteger no = new BigInteger(1, messageDigest);
					hashtext = no.toString(16);
					while (hashtext.length() < 32) {
						hashtext = "0" + hashtext;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new RuntimeException(e);
				}
				return hashtext;
			}

			@Override
			public boolean matches(CharSequence rawPassword, String prefixEncodedPassword) {
				if (rawPassword == null && prefixEncodedPassword == null) {
					return true;
				} else {
					String delegate = this.encode(rawPassword);
					if (delegate.matches(prefixEncodedPassword)) {
						return true;
					} else {
						return false;
					}
				}
			}
		};

		System.out.println(passwordEncoder.encode("password"));


	}

}
