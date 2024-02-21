package com.kjq.config;

import com.kjq.utils.MD5Util;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
public class MD5PasswordEncoder implements PasswordEncoder {

    //对密码进行加密
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encode((String) rawPassword);
    }
    //对密码进行比较
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
    }
}
