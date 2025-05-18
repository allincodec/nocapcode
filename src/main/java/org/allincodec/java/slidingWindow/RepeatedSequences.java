package org.allincodec.java.slidingWindow;

import java.util.*;

/****
 * ============== Repeated Sequences ==================
 * Problem Statement:
 * Given a string, s, that represents a DNA sequence, return all the 10-letter-long
 * sequences that occur more than once in the DNA string. The output can be
 * in any order.
 * <p>
 * Constraints:
 *  ==>  1 <= s.length <= 10^3
 *  ==>  s[i] is either 'A','C', 'G', or 'T'.
 * <p>
 * Example:
 * For the DNA string: "ATATTGGCCAATTGGCCAATTCGC"
 *
 * We scan 10-letter windows through the string:
 * 0:  ATATTGGCCA
 * 1:  TATTGGCCAA
 * 2:  ATTGGCCAAT
 * 3:  TTGGCCAATT
 * 4:  TGGCCAATTG
 * 5:  GGCCAATTGG
 * 6:  GCCAATTGGC
 * 7:  CCAATTGGCC
 * 8:  CAATTGGCCA
 * 9:  AATTGGCCAA
 * 10: ATTGGCCAAT (matches window 2)
 * 11: TTGGCCAATT (matches window 3)
 * ...
 *<p>
 * Output: ["ATTGGCCAAT", "TTGGCCAATT"]
 * <p>
 * Implementation Approach:
 * - Use a HashSet to track sequences we've seen
 * - Use another HashSet to track repeated sequences
 * - Iterate through all possible 10-letter windows
 * - Return the list of repeated sequences
 *<p>
 * Time Complexity: O(n), where n is the length of the string
 * Space Complexity: O(n), for storing the sequences in the HashSets
 *
 */
public class RepeatedSequences {

    public static List<String> repeatedSequences(String s) {
        if (s.length() < 10) return List.of();

        var seen = new HashSet<String>();
        var repeated = new HashSet<String>();

        for (int i = 0; i <= s.length() - 10; i++) {
            var sequence = s.substring(i, i + 10);
            if (!seen.add(sequence)) repeated.add(sequence);
        }

        return repeated.stream().toList();
    }

    public static void main(String[] args) {
        System.out.println(repeatedSequences("ATATTGGCCAATTGGCCAATTCGC"));
        System.out.println(repeatedSequences("ATATTGGCCAATATTGGCCA"));
    }

}
