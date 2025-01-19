public class Minimize_XOR {

    public static int minimizeXor(int num1, int num2) {
        int currentBits = Integer.bitCount(num1);
        int requiredBits = Integer.bitCount(num2);

        String binaryStr = Integer.toBinaryString(num1);
        StringBuilder sb = new StringBuilder(binaryStr);

        // If needed, pad with leading zeros to ensure consistent length
        while (sb.length() < 32) {
            sb.insert(0, '0');
        }

        if (currentBits < requiredBits) {
            int extraBits = requiredBits - currentBits;

            // Add extra set bits from the right
            for (int i = sb.length() - 1; i >= 0 && extraBits > 0; i--) {
                if (sb.charAt(i) == '0') {
                    sb.setCharAt(i, '1'); // Set the bit
                    extraBits--;
                }
            }
        } else if (currentBits > requiredBits) {
            int excessBits = currentBits - requiredBits;

            // Remove extra set bits from the right
            for (int i = sb.length() - 1; i >= 0 && excessBits > 0; i--) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0'); // Unset the bit
                    excessBits--;
                }
            }
        }
        
        return Integer.parseInt(sb.toString(), 2);
    }

    public static boolean isSet(int x, int bit) {
        return (x & (1 << bit)) != 0;
    }

    public static int setBit(int x, int bit) {
        return x | (1 << bit);
    }

    public static int unsetBit(int x, int bit) {
        return x & ~(1 << bit);
    }

    public static int minimizeXor2(int num1, int num2) { 
        int currentBits = Integer.bitCount(num1);
        int requiredBits = Integer.bitCount(num2);
        int x = num1;

        int bit = 0;
        if(currentBits < requiredBits) {
            while(currentBits < requiredBits) {
                if(!isSet(x, bit)) {
                    x = setBit(x, bit);
                    currentBits++;
                }
                bit++;
            }
        }else {
            while(currentBits > requiredBits) {
                if(isSet(x, bit)) {
                    x = unsetBit(x, bit);
                    currentBits--;
                }
                bit++;
            }
        }

        return x;
    }
    public static void main(String[] args) {
        int num1 = 3;
        int num2 = 5;

        System.out.println("ANS = " + Integer.bitCount(5));

        System.out.println("OUTPUT : " + minimizeXor2(num1, num2));
    }
}