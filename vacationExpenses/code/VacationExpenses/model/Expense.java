package VacationExpenses.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Serializable {
    private int id;  // Уникальный идентификатор расхода
    private String type;  // Тип расхода (например, "Трансферы", "Питание")
    private double amount; // Сумма расхода
    private LocalDate date; // Дата расхода

    // Конструктор с id
    public Expense(int id, String type, double amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    // Переопределение equals и hashCode для корректного сравнения объектов Expense
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Expense expense = (Expense) obj;
        return id == expense.id; // Сравниваем по id, так как это уникальный идентификатор
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
