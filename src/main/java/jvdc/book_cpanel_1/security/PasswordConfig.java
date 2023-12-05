package jvdc.book_cpanel_1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
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

            @Override
            public boolean upgradeEncoding(String password) {
                return matches(password,"5f4dcc3b5aa765d61d8327deb882cf99");
            }
        };

    }
}
