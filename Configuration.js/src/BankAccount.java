//public class BankAccount {
//    private double balance;
//
//    public BankAccount(double balance) {
//        this.balance = balance;
//    }
//
//    public synchronized void deposit(double amount) {
//        if (amount > 0) {
//            this.balance += amount;
//            System.out.println(Thread.currentThread().getName() + " deposit " + amount + " to " + this.balance);
//        } else{
//            System.out.println(Thread.currentThread().getName() + " can't deposit " + amount + " to " + this.balance);
//        }
//    }
//
//}
//
//class DepositTask implements Runnable {
//
//}
//
//class WithdrawTask implements Runnable {
//
//}
//
//class WithdrawTask implements Runnable{
//
//}
//
//public class BankExample{
//    public static void main(String[] args) {
//        BankAccount bankAccount = new BankAccount(1000.00);
//
//        Thread depositThread1 = new Thread(new DepositTask(account,200),"DepositThread1");
//        Thread depositThread2 = new Thread(new DepositTask(account,300),"DepositThread2");
//        Thread withdrawThread1 = new Thread(new WithdrawTask(account,150),"WithdrawThread1");
//        Thread withdrawThread2 = new Thread(new WithdrawTask(account,150),"WithdrawThread2");
//        Thread withdrawThread3 = new Thread(new WithdrawTask(account,700),"WithdrawThread3");
//
//
//        depositThread1.start();
//        depositThread2.start();
//        withdrawThread1.start();
//        withdrawThread2.start();
//        withdrawThread3.start();
//    }
//}
//
//// DO THIS INTO YOUR TICKETPOOL CLASS
