/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartuni.classes.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Shalitha Suranga
 */
public class SmartUniMain {
    
    
    public static String getPasswordHash(String password){
        String encPass =DigestUtils.sha256Hex(password);
        return encPass;
    
    }
    
   
    

    public static void main(String[] args) {

    }
    
}


