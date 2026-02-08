package org.allincodec.twopointer;

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
       char[] chars = s.toCharArray();
       int i =0;
       int j = chars.length-1;

       while(i <= j) {
           if(notACharAndNumeric(chars[i])) {
                i++;
                continue;
           } else if(notACharAndNumeric(chars[j])) {
               j--;
               continue;
           }
           else if(!isSame(chars[i], chars[j])) {
                   return false;
           }
           i++;
           j--;
       }

       return true;
    }

    private static boolean isSame(char aChar, char aChar1) {
        return aChar == aChar1 || aChar == aChar1 + 32 || aChar == aChar1 - 32;
    }


    static boolean notACharAndNumeric(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("kaYak"));
        System.out.println(isPalindrome("RaCEACAR"));
        System.out.println(isPalindrome("Madam, in Eden, Im Adam"));
        System.out.println(isPalindrome("}u"));
    }
}
