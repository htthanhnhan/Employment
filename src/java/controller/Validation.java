/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author NguyenVy
 */
public class Validation extends Exception {

    String str;

    public Validation(String str) {
        this.str = str;
    }

    static boolean CheckInput(String str) throws Validation {
        if (str == null || str.length() < 0) {
            throw new Validation(str);
        }
        str = str.trim();
        if (str.equals("")) {
            throw new Validation(str);
        }
        return true;
    }

    static String CheckTel(String input) {
        if (input.length() != 10) {
            throw new RuntimeException("Vui lòng nhập số điện thoại có độ dài 10 ký tự!");
        }
        try {
            if (Validation.CheckInput(input)) {
                Integer.valueOf(input);
                return input;
            }
        } catch (Validation ex) {
            throw new RuntimeException("Vui lòng nhập số điện thoại dạng số");
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Vui lòng nhập số điện thoại dạng số");
        }
        return input;
    } 
}
