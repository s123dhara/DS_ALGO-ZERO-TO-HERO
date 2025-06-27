import java.util.Arrays;

class ATM {
    private int[] bankNotes = { 20, 50, 100, 200, 500 };
    private int[] banknotesCount;

    public ATM() {
        // this.bankNotes=;
        this.banknotesCount = new int[5];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            this.banknotesCount[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] result = new int[5]; // This will track the used Notes
        for (int i = 4; i >= 0; i--) {
            if (amount >= bankNotes[i]) {
                int count = amount / bankNotes[i];

                if (count > this.banknotesCount[i]) {
                    count = this.banknotesCount[i];
                }
                result[i] = count;
                amount -= bankNotes[i] * count;
            }

            if (amount == 0) {
                for (int j = 0; j < 5; j++) {
                    banknotesCount[j] -= result[j]; // Total Notes - Used Notes = remaining Notes
                }
                return result;
            }
        }
        return new int[] { -1 };
    }
}

public class Design_an_ATM_Machine {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(new int[] { 0, 0, 1, 2, 1 });
        System.out.println(Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[] { 0, 1, 0, 1, 1 });
        System.out.println(Arrays.toString(atm.withdraw(600)));
        System.out.println(Arrays.toString(atm.withdraw(550)));

    }
}