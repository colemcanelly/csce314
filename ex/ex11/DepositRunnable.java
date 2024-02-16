import java.util.concurrent.locks.*;


/*
 * P1 is Depositing
 * P2 is Withdrawing
 */
public class DepositRunnable implements Runnable { // try to deposit
    public void run() {
        try {
            account.deposit(amount);
        } catch (InterruptedException exception) {
        }
    }

    private BankAccount account;
    private double amount;
}

public class WithdrawRunnable implements Runnable { // try to withdraw
    public void run() {
        try {
            account.withdraw(amount);
        } catch (InterruptedException exception) {
        }
    }

    private BankAccount account;
    private double amount;
}

public class BankAccount {
    private double balance;
    private Lock balanceChangeLock;             // (1)
    private Condition sufficientFundsCondition; // (2)

    public BankAccount() {
        balance = 0;
        balanceChangeLock = new ReentrantLock();
        sufficientFundsCondition = balanceChangeLock.newCondition(); // (3)
    }
    // P1
    public void deposit(double amount) {
        balanceChangeLock.lock();                   // (4)
        try {
            double nb = balance + amount;           // (5)
            balance = nb;                           // (6)
            sufficientFundsCondition.signalAll();   // (7)
        } finally {
            balanceChangeLock.unlock(); // (8)
        }
    }
    // P2
    public void withdraw(double amount)
            throws InterruptedException {
        balanceChangeLock.lock();                   // (9)
        try {
            while (balance < amount)                // (10)
                sufficientFundsCondition.await();   // (11)
            double nb = balance - amount;           // (12)
            balance = nb;                           // (13)
        }
        finally {
            balanceChangeLock.unlock();             // (14)
        }
    }
}

/*
 * ATTEMPT 1
 * 1.   (5) and (6)
 * 2.   (12) and (13)
 * 3.   interleave
 * 4.   race hazard
 * 5.   race hazard
 * 6.   deadlock
 * 7.   balanceChangeLock
 * 8.   sufficientFundsCondition
 * 9.   P2
 * 10.  P1
 * 11.  P1
 * 12.  P2
 */