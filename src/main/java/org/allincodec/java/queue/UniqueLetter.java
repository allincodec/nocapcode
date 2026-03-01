package org.allincodec.java.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class UniqueLetter {

    public String solve(String A) {
        int[] freq = new int[26];
        Deque<Character> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c : A.toCharArray()) {
            freq[c - 'a']++;
            q.offer(c);
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.poll();
            }
            sb.append(q.isEmpty() ? '#' : q.peek());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        UniqueLetter uq = new UniqueLetter();
        System.out.println(uq.solve("abcabc"));
        System.out.println(uq.solve("iergxwhddh"));
        System.out.println(uq.solve("jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasluuwooupcyxwgl"));
        System.out.println(uq.solve("xxikrwmjvsvckfrqxnibkcasompsuyuogauacjrr"));
    }
}
