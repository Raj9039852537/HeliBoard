package helium314.keyboard.latin;

import android.util.Log;

import java.util.Arrays;
import java.util.Locale;

public class TextTransformer {

    public String transformText(String input, String transformationPattern) {
        // Validate input and transformation pattern
        if (input == null || transformationPattern == null || transformationPattern.isEmpty()) {
            return input;
        }
        int len = transformationPattern.length();  // Length of the transformation pattern
        StringBuilder result = new StringBuilder();
        if(len == 5){
            for (char c : input.toCharArray()) {
                result.append(c);
                if(c!=' ') result.append(transformationPattern.charAt(1));  // Adding combining underline character
            }
            return result.toString();
        } else {
        switch (transformationPattern){
            case "low" : return input.toLowerCase();
            case "upp" : return input.toUpperCase();
            //case "1st" : return input.(input);
            case "bold" : return applyBold(input);
            case "italic" : return applyItalic(input);
            case "underline" : return applyUnderline(input);
            case "upside_down" : return applyUpsideDown(input);
            case "calligraphic" : return applyCalligraphic(input);
            case "fraktur" : return applyFraktur(input);
            case "circled" : return applyCircled(input);
            case "strikethrough" : return applyStrikethrough(input);
            case "sub" : return applySubscript(input);
            case "sup" : return applySuperscript(input);
            case "doublevision" : return applyDoubleVision(input);
            case "wide" : return applyWide(input);
            case "big" : return applyBig(input);

        }
        boolean hs = false;
            int[] codePoints = new int[transformationPattern.length()];
            for (int i = 0; i < transformationPattern.length(); i++) {
                codePoints[i] = transformationPattern.codePointAt(i);  // Get the code point of the character at index i
                if (Character.isHighSurrogate(transformationPattern.charAt(i))) {
                    i++;
                    hs = true;
                }

            }


            // Loop through each Unicode code point in the input string
            int i = 0;
            while (i < input.length()) {
                // Get the Unicode code point at the current position
                int codePoint = input.codePointAt(i);

                // Initialize transformedCodePoint as the original code point
                int transformedCodePoint = codePoint;

                // Handle lowercase letters (a-z)
                if (Character.isLowerCase(codePoint)) {
                    int index = codePoint - 'a';  // Calculate index for a-z (0 to 25)
                    if (hs) index = index * 2;
                    transformedCodePoint = codePoints[index];
                    Log.d("transformedCodePoint", Arrays.toString(codePoints) + " " + String.valueOf(index) + " " + String.valueOf(transformedCodePoint));
                }
                // Handle uppercase letters (A-Z)
                else if (Character.isUpperCase(codePoint)) {
                    int index = codePoint - 'A';  // Calculate index for A-Z (0 to 25)
                    if (hs) index = index * 2;
                    transformedCodePoint = codePoints[index];
                }
                // Non-alphabet characters remain unchanged
                else {
                    transformedCodePoint = codePoint;
                }
                // Append the transformed code point (could be a surrogate pair or single code point)
                result.appendCodePoint(transformedCodePoint);

                // Move to the next code point (handle surrogate pairs correctly)
                i += Character.charCount(codePoint);
            }

        }
        return result.toString();
    }


    // Helper method to transform a single character based on the transformation pattern
    private static char transformCharacter(int codePoint, String transformationPattern, int len) {
        char newChar = (char) codePoint;

        // For lowercase letters (a-z)
        if (Character.isLowerCase(newChar)) {
            int index = newChar - 'a';
            if (index >= 0 && index < 26) {
                newChar = transformationPattern.charAt(index);
            }
        }
        // For uppercase letters (A-Z)
        else if (Character.isUpperCase(newChar)) {
            int index = newChar - 'A';
            if (index >= 0 && index < 26) {
                // If the transformation pattern is large enough (for both uppercase and lowercase)
                if (len > 26) {
                    newChar = transformationPattern.charAt(26 + index); // Uppercase letters from pattern[26..]
                } else {
                    newChar = transformationPattern.charAt(index); // Fallback to first 26 characters
                }
            }
        }

        return newChar;
    }

    // Helper method to transform code points that are outside the BMP (surrogate pair code points)
    private static char transformCodePoint(int codePoint, String transformationPattern, int len) {
        char newChar = (char) codePoint;

        // For surrogate pairs or supplementary characters (code points > 0xFFFF)
        if (codePoint > 0xFFFF) {
            // Here we can apply transformations similar to the character-based ones
            // This example handles code points in the supplementary range (higher than 0xFFFF)
            int index = codePoint - 0x10000;  // Map to index in the transformation pattern (arbitrary example)

            // Ensure the index is within bounds
            if (index < len) {
                newChar = transformationPattern.charAt(index);
            }
        }

        return newChar;
    }


    // Method to apply bold transformation to a string
    private String applyBold(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getBoldCharacter(c));
        }
        return result.toString();
    }
    private String applyWide(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(new String(Character.toChars(0xFF41 + (c - 'a'))));
        }
        return result.toString();
    }
    private String applyBig(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) result.append(new String(Character.toChars(0x1F1E6 + (c - 'a')))).append(Character.toChars(0x200C));
            if (Character.isUpperCase(c)) result.append(new String(Character.toChars(0x1F1E6 + (c - 'A')))).append(Character.toChars(0x200C));
          //          if(c!=' ') result.append(Character.toChars(0x200C));  // Convert to bold character
        }
        return result.toString();
    }

    // Helper method to convert a character to its bold Unicode equivalent
    private String getBoldCharacter(char c) {
        if (Character.isUpperCase(c)) {
            // Convert uppercase letter to bold (U+1D400 to U+1D419)
            return new String(Character.toChars(0x1D400 + (c - 'A')));  // Convert to bold character
        } else if (Character.isLowerCase(c)) {
            // Convert lowercase letter to bold (U+1D41A to U+1D433)
            return new String(Character.toChars(0x1D41A + (c - 'a')));  // Convert to bold character
        } else if (Character.isDigit(c)) {
            // Convert digits to bold (U+1D7E0 to U+1D7F7)
            return new String(Character.toChars(0x1D7E0 + (c - '0')));  // Convert to bold character
        }

        // For non-letter and non-digit characters, return as is (no transformation)
        return Character.toString(c);
    }


    // Method to apply italic transformation to a string
    private String applyItalic(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getItalicCharacter(c));
        }
        return result.toString();
    }

    // Helper method to convert a character to its italic Unicode equivalent
    private String getItalicCharacter(char c) {
        if (Character.isUpperCase(c)) {
            // Convert uppercase letter to italic (U+1D434 to U+1D44D)
            return new String(Character.toChars(0x1D434 + (c - 'A')));  // Convert to italic character
        } else if (Character.isLowerCase(c)) {
            // Convert lowercase letter to italic (U+1D44E to U+1D467)
            return new String(Character.toChars(0x1D44E + (c - 'a')));  // Convert to italic character
        } else if (Character.isDigit(c)) {
            // Convert digits to italic (U+1D7E8 to U+1D7FF)
            return new String(Character.toChars(0x1D7E8 + (c - '0')));  // Convert to italic character
        }

        // For non-letter and non-digit characters, return as is (no transformation)
        return Character.toString(c);
    }

    // Apply underline transformation using combining characters
    private String applyUnderline(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(c).append("\u0332");  // Adding combining underline character
        }
        return result.toString();
    }

    // Apply Calligraphic (Cursive) transformation
    private String applyCalligraphic(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getCalligraphicCharacter(c));
        }
        return result.toString();
    }

    // Convert to Calligraphic (Cursive) Unicode characters
    private String getCalligraphicCharacter(char c) {
        if (Character.isUpperCase(c)) {
            // Convert uppercase letter to calligraphic (U+1D49C to U+1D4B5)
            return new String(Character.toChars(0x1D49C + (c - 'A')));  // Convert to calligraphic character
        } else if (Character.isLowerCase(c)) {
            // Convert lowercase letter to calligraphic (U+1D4B6 to U+1D4CF)
            return new String(Character.toChars(0x1D4B6 + (c - 'a')));  // Convert to calligraphic character
        }

        // For non-letter characters, return as is (no transformation)
        return Character.toString(c);
    }

    // Apply Fraktur transformation
    private String applyFraktur(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getFrakturCharacter(c));
        }
        return result.toString();
    }

    // Convert to Fraktur Unicode characters
    private String  getFrakturCharacter(char c) {
        if (Character.isLowerCase(c)) {
            return new String(Character.toChars(c + 0x1D51E - 'a'));  // Fraktur lowercase letters
        } else if (Character.isUpperCase(c)) {
            return new String(Character.toChars(c + 0x1D504 - 'A'));  // Fraktur uppercase letters
        }
        return Character.toString(c);
    }

    // Apply Strikethrough transformation using combining characters
    private String applyStrikethrough(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(c).append("\u0336");  // Adding combining strikethrough character
        }
        return result.toString();
    }

    // Apply Subscript transformation
    private String applySubscript(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getSubscriptCharacter(c));
        }
        return result.toString();
    }

    // Convert to subscript Unicode characters
    private String getDoubleVisionCharacter(char c) {
        //"aͣbcͨdͩeͤfghͪiͥjͥklmͫnoͦpqrͬstͭuͧvͮwxͯyz",
        if (c == 'a') return "aͣ";
        if (c == 'c') return "cͨ";
        if (c == 'd') return "dͩ";
        if (c == 'e') return "eͤ";
        if (c == 'h') return "hͪ";
        if (c == 'i') return "iͥ";
        if (c == 'm') return "mͫ";
        if (c == 'o') return "oͦ";
        if (c == 'r') return "rͬ";
        if (c == 't') return "tͭ";
        if (c == 'u') return "uͧ";
        if (c == 'v') return "vͮ";
        if (c == 'x') return "xͯ";
        return String.valueOf(c);
    }
    private String applyDoubleVision(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getDoubleVisionCharacter(c));
        }
        return result.toString();
    }

    // Convert to subscript Unicode characters
    private char getSubscriptCharacter(char c) {
        if (c == 'a') return 'ₐ';
        if (c == 'e') return 'ₑ';
        if (c == 'i') return 'ᵢ';
        if (c == 'o') return 'ₒ';
        if (c == 'u') return 'ᵤ';
        if (c == 'n') return 'ₙ';
        if (c == 'p') return 'ₚ';
        if (c == 'r') return 'ᵣ';
        if (c == 't') return 'ₜ';
        if (Character.isDigit(c)) return (char) (c + 0x2080 - '0'); // Subscript digits (0 to 9)
        return c;
    }

    // Apply Superscript transformation
    private String applySuperscript(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getSuperscriptCharacter(c));
        }
        return result.toString();
    }

    // Convert to superscript Unicode characters
    private char getSuperscriptCharacter(char c) {
        if (c == 'a') return 'ᵃ';
        if (c == 'b') return 'ᵇ';
        if (c == 'c') return 'ᶜ';
        if (c == 'd') return 'ᵈ';
        if (c == 'e') return 'ᵉ';
        if (c == 'f') return 'ᶠ';
        if (c == 'g') return 'ᵍ';
        if (c == 'h') return 'ʰ';
        if (c == 'i') return 'ⁱ';
        if (c == 'j') return 'ʲ';
        if (c == 'k') return 'ᵏ';
        if (c == 'l') return 'ˡ';
        if (c == 'm') return 'ᵐ';
        if (c == 'n') return 'ⁿ';
        if (c == 'o') return 'ᵒ';
        if (c == 'p') return 'ᵖ';
        if (c == 'q') return 'q';
        if (c == 'r') return 'ʳ';
        if (c == 's') return 'ˢ';
        if (c == 't') return 'ᵗ';
        if (c == 'u') return 'ᵘ';
        if (c == 'v') return 'ᵛ';
        if (c == 'w') return 'ʷ';
        if (c == 'x') return 'ˣ';
        if (c == 'y') return 'ᵧ';
        if (c == 'z') return 'ᶻ';
        if (Character.isDigit(c)) return (char) (c + 0x2070 - '0'); // Superscript digits (0 to 9)
        return c;
    }

    // Apply Circled transformation
    private String applyCircled(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getCircledCharacter(c));
        }
        return result.toString();
    }

    // Convert to circled Unicode characters
    private String getCircledCharacter(char c) {
        if (Character.isLowerCase(c)) {
            return new String(Character.toChars(c + 0x1F150 - 'a'));  // Circled lowercase letters
        } else if (Character.isUpperCase(c)) {
            return new String(Character.toChars(c + 0x1F170 - 'A'));  // Circled uppercase letters
        }
        return Character.toString(c);
    }

    // Apply Bubble text using Unicode circled characters
    private String applyBubbleText(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getBubbleCharacter(c));
        }
        return result.toString();
    }

    // Convert character to bubble Unicode (circled)
    private String getBubbleCharacter(char c) {
        if (Character.isLowerCase(c)) {
            return new String(Character.toChars(c + 0x1D552 - 'a'));  // Circled lowercase letters
        } else if (Character.isUpperCase(c)) {
            return new String(Character.toChars(c + 0x1D538 - 'A'));  // Circled uppercase letters
        }
        return Character.toString(c);
    }

    // Apply Upside-down text (simple reverse of text)
    private String applyUpsideDown(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(getUpsideDownCharacter(c));
        }
        return result.reverse().toString();  // Reverse the entire string
    }

    // Convert character to upside-down Unicode (using rotated chars)
    private char getUpsideDownCharacter(char c) {
        // Map basic upside-down characters (this is an extended list)
        switch (c) {
            case 'a': return 'ɒ';
            case 'b': return 'q';
            case 'c': return 'ɔ';
            case 'd': return 'p';
            case 'e': return 'ǝ';
            case 'f': return 'ɟ';
            case 'g': return 'ƃ';
            case 'h': return 'ɥ';
            case 'i': return 'ᴉ';
            case 'j': return 'ɾ';
            case 'k': return 'ʞ';
            case 'l': return 'ʇ';
            case 'm': return 'ɯ';
            case 'n': return 'u';
            case 'o': return 'o';
            case 'p': return 'd';
            case 'q': return 'b';
            case 'r': return 'ɹ';
            case 's': return 's';
            case 't': return 'ʇ';
            case 'u': return 'n';
            case 'v': return 'ʌ';
            case 'w': return 'ʍ';
            case 'x': return 'x';
            case 'y': return 'ʎ';
            case 'z': return 'z';
            default: return c;
        }
    }
}
