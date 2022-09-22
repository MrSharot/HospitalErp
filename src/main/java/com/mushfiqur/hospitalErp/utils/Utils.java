package com.mushfiqur.hospitalErp.utils;

import com.mushfiqur.hospitalErp.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponseEntity<String> getErrorMessages(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity getMessageResponse(String msg, HttpStatus status) {
        return new ResponseEntity(new MessageDto(msg), status);
    }
}
