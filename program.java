/**
 * program
 */
public class program {
public static void main(String[] args) {
    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.Map;
    import java.util.Scanner;
    import java.util.Set;
    
    public class Laptop {
        // Поля класса
        private String brand;
        private int ram; // ОЗУ в ГБ
        private int storage; // Объем ЖД в ГБ
        private String os; // Операционная система
        private String color;
    
        // Конструктор
        public Laptop(String brand, int ram, int storage, String os, String color) {
            this.brand = brand;
            this.ram = ram;
            this.storage = storage;
            this.os = os;
            this.color = color;
        }
    
        // Геттеры
        public String getBrand() { return brand; }
        public int getRam() { return ram; }
        public int getStorage() { return storage; }
        public String getOs() { return os; }
        public String getColor() { return color; }
    
        // Метод для отображения информации о ноутбуке
        @Override
        public String toString() {
            return "Laptop [Brand=" + brand + ", RAM=" + ram + "GB, Storage=" + storage + "GB, OS=" + os + ", Color=" + color + "]";
        }
    
        // Метод фильтрации ноутбуков по критериям
        public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> criteria) {
            Set<Laptop> filteredLaptops = new HashSet<>();
    
            for (Laptop laptop : laptops) {
                boolean matches = true;
    
                if (criteria.containsKey("ram") && laptop.getRam() < (int) criteria.get("ram")) {
                    matches = false;
                }
                if (criteria.containsKey("storage") && laptop.getStorage() < (int) criteria.get("storage")) {
                    matches = false;
                }
                if (criteria.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) criteria.get("os"))) {
                    matches = false;
                }
                if (criteria.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) criteria.get("color"))) {
                    matches = false;
                }
    
                if (matches) {
                    filteredLaptops.add(laptop);
                }
            }
    
            return filteredLaptops;
        }
    
        // Основной метод
        public static void main(String[] args) {
            // Создаем множество ноутбуков
            Set<Laptop> laptops = new HashSet<>();
            laptops.add(new Laptop("Dell", 8, 512, "Windows", "Black"));
            laptops.add(new Laptop("HP", 16, 1024, "Windows", "Silver"));
            laptops.add(new Laptop("Apple", 8, 256, "macOS", "Gray"));
            laptops.add(new Laptop("Asus", 16, 512, "Windows", "Black"));
    
            // Запрашиваем критерии фильтрации у пользователя
            Scanner scanner = new Scanner(System.in);
            Map<String, Object> criteria = new HashMap<>();
    
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
    
            switch (choice) {
                case 1:
                    System.out.println("Введите минимальное значение ОЗУ (в ГБ):");
                    int ram = scanner.nextInt();
                    criteria.put("ram", ram);
                    break;
                case 2:
                    System.out.println("Введите минимальное значение объема ЖД (в ГБ):");
                    int storage = scanner.nextInt();
                    criteria.put("storage", storage);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.nextLine();
                    criteria.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.nextLine();
                    criteria.put("color", color);
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
    
            // Фильтруем и выводим ноутбуки
            Set<Laptop> filteredLaptops = filterLaptops(laptops, criteria);
            if (filteredLaptops.isEmpty()) {
                System.out.println("Нет ноутбуков, удовлетворяющих условиям.");
            } else {
                System.out.println("Ноутбуки, удовлетворяющие условиям:");
                for (Laptop laptop : filteredLaptops) {
                    System.out.println(laptop);
                }
            }
            
            scanner.close();
        }
    }
    