package VacationExpenses.dao;

import VacationExpenses.model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileExpenseDAO implements ExpenseDAO {

    private List<Expense> expenses;  // Список расходов

    // Конструктор, который загружает данные при инициализации
    public FileExpenseDAO() {
        expenses = new ArrayList<>();
    }

    @Override
    public boolean addExpense(Expense expense) {
        // Добавление расхода в список
        return expenses.add(expense);
    }

    @Override
    public int removeExpense(int id) {
        // Удаление расхода по id
        Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            Expense expense = iterator.next();
            if (expense.getId() == id) {
                iterator.remove();
                return 1;  // Удалили один элемент
            }
        }
        return 0;  // Если расход с таким id не найден
    }

    @Override
    public Expense update(int id, Expense newExpense) {
        // Обновление расхода по id
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (expense.getId() == id) {
                expenses.set(i, newExpense);
                return newExpense;
            }
        }
        return null;  // Если расход с таким id не найден
    }

    @Override
    public int quantity() {
        // Возвращаем количество расходов в списке
        return expenses.size();
    }

    @Override
    public void printExpense(Expense expense) {
        // Печать информации о расходе
        System.out.println("Expense ID: " + expense.getId() +
                ", Type: " + expense.getType() +
                ", Amount: " + expense.getAmount() +
                ", Date: " + expense.getDate());
    }

    @Override
    public double expensesByCategory(String type) {
        // Суммируем расходы по категории (типу)
        return expenses.stream()
                .filter(expense -> expense.getType().equalsIgnoreCase(type)) // Фильтруем по типу
                .mapToDouble(Expense::getAmount) // Извлекаем сумму расхода
                .sum(); // Суммируем
    }

    @Override
    public double expenseByDateRange(LocalDate from, LocalDate to) {
        // Суммируем расходы по диапазону дат
        return expenses.stream()
                .filter(expense -> !expense.getDate().isBefore(from) && !expense.getDate().isAfter(to)) // Фильтруем по датам
                .mapToDouble(Expense::getAmount) // Извлекаем сумму расхода
                .sum(); // Суммируем
    }

    @Override
    public void saveTasks(String fileName) {
        // Сохранение списка расходов в файл с помощью сериализации
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(expenses); // Сохраняем список расходов
            System.out.println("Expenses saved successfully to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    @Override
    public void loadTasks(String fileName) {
        // Загрузка списка расходов из файла с помощью десериализации
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            expenses = (List<Expense>) in.readObject(); // Загружаем список расходов из файла
            System.out.println("Expenses loaded successfully from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
