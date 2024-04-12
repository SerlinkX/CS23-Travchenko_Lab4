import java.util.HashMap;
import java.util.Map;

// Клас, що представляє карткову систему метро
public class MetroCardSystem {
    private Map<Integer, MetroCard> cards; // Колекція для зберігання карток

    // Конструктор
    public MetroCardSystem() {
        this.cards = new HashMap<>();
    }

    // Операція видачі нової картки та її реєстрації в системі
    public void issueNewCard(int studentId) {
        MetroCard card = new MetroCard(studentId);
        cards.put(studentId, card);
        System.out.println("New card issued for student with ID: " + studentId);
    }

    // Операція отримання інформації про клієнта
    public void getCustomerInfo(int studentId) {
        if (cards.containsKey(studentId)) {
            MetroCard card = cards.get(studentId);
            System.out.println("Customer ID: " + studentId);
            System.out.println("Balance: " + card.getBalance());
        } else {
            System.out.println("Customer with ID " + studentId + " not found.");
        }
    }

    // Операція поповнення рахунку
    public void topUpBalance(int studentId, double amount) {
        if (cards.containsKey(studentId)) {
            MetroCard card = cards.get(studentId);
            card.topUp(amount);
            System.out.println("Balance topped up for customer with ID " + studentId + ". New balance: " + card.getBalance());
        } else {
            System.out.println("Customer with ID " + studentId + " not found.");
        }
    }

    // Операція оплати поїздки
    public void payForTrip(int studentId, double fare) {
        if (cards.containsKey(studentId)) {
            MetroCard card = cards.get(studentId);
            if (card.getBalance() >= fare) {
                card.deduct(fare);
                System.out.println("Payment successful for customer with ID " + studentId + ". New balance: " + card.getBalance());
            } else {
                System.out.println("Insufficient balance for customer with ID " + studentId);
            }
        } else {
            System.out.println("Customer with ID " + studentId + " not found.");
        }
    }

    // Операція отримання залишку коштів на картці
    public double getBalance(int studentId) {
        if (cards.containsKey(studentId)) {
            MetroCard card = cards.get(studentId);
            return card.getBalance();
        } else {
            System.out.println("Customer with ID " + studentId + " not found.");
            return -1; // Повертаємо -1 як помилку
        }
    }
}

// Клас, що представляє картку метро
class MetroCard {
    private int studentId; // Ідентифікатор студента
    private double balance; // Баланс картки

    // Конструктор
    public MetroCard(int studentId) {
        this.studentId = studentId;
        this.balance = 0.0;
    }

    // Метод для поповнення балансу
    public void topUp(double amount) {
        balance += amount;
    }

    // Метод для зняття коштів
    public void deduct(double amount) {
        balance -= amount;
    }

    // Метод для отримання балансу
    public double getBalance() {
        return balance;
    }
}

// Клас з методом main для тестування системи
class Main {
    public static void main(String[] args) {
        MetroCardSystem metroSystem = new MetroCardSystem();

        // Тестуємо операції
        metroSystem.issueNewCard(123456);
        metroSystem.topUpBalance(123456, 20.0);
        metroSystem.payForTrip(123456, 2.5);
        metroSystem.getCustomerInfo(123456);
    }
}