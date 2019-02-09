package multithread;


import java.util.concurrent.TimeUnit;

public class Operations {
    public static void main(String[] args) {

        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(() -> {
            transfer(a, b, 500);
        }).start();
        transfer(b, a, 300);
    }

    static void transfer(Account acc1, Account acc2, int amount){

        try {
            if (acc1.getBalance() < amount) throw new InsufficientFundsException();
            {
                if (acc1.getLock().tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        Thread.sleep(1000);
                        if (acc2.getLock().tryLock(3,TimeUnit.SECONDS)) try {
                            acc1.withdraw(amount);
                            acc2.withdraw(amount);
                            System.out.println("Перевод успешен остаток на баллансе:" + (acc1.getBalance()));
                        } finally {
                            acc2.getLock().unlock();
                        }
                    } finally {
                        acc1.getLock().unlock();
                    }
                }else System.out.println("Лок занят");



            }
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

