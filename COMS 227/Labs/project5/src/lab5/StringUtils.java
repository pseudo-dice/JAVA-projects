package lab5;

public class StringUtils {
	public static String getInitials(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return "";
        }
        
        String initials = "";
        boolean newWord = true;

        for (int i = 0; i < fullName.length(); i++) {
            char ch = fullName.charAt(i);
            if (Character.isLetter(ch)) {
                if (newWord) {
                	initials += ch;
                    newWord = false;
                }
            } else {
                newWord = true;
            }
        }

        return initials.toString();
    }

    public static int firstVowelIndex(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (isVowel(ch)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public static void main(String[] args) {
        // Test getInitials method
        System.out.println("Initials for 'Cher': " + getInitials("Cher"));
        System.out.println("Initials for 'Edna del Humboldt von der Schooch': " + getInitials("Edna del Humboldt von der Schooch"));

        // Test firstVowelIndex method
        System.out.println("First vowel index in 'Hello': " + firstVowelIndex("Hello"));
        System.out.println("First vowel index in 'Sky': " + firstVowelIndex("Sky"));
        System.out.println("First vowel index in 'Rhythm': " + firstVowelIndex("Rhythm"));
    }
}

