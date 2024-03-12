class Fruit {
    // Класс фрукта
}

class Apple extends Fruit {
    public static final float WEIGHT = 1.0f; // Вес яблока
}

class Orange extends Fruit {
    public static final float WEIGHT = 1.5f; // Вес апельсина
}

class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    // Добавление фрукта в коробку
    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    // Вычисление веса коробки
    public float getWeight() {
        return fruits.size() * fruits.get(0).WEIGHT;
    }

    // Сравнение коробок по весу
    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    // Пересыпание фруктов из текущей коробки в другую
    public void transferFruitsToAnotherBox(Box<T> anotherBox) {
        if (this == anotherBox) {
            return; // Нельзя пересыпать в саму себя
        }

        anotherBox.fruits.addAll(this.fruits);
        this.fruits.clear(); // Очистка текущей коробки после пересыпания
    }
}

public class Main {
    public static void main(String[] args) {
        // Пример использования
        Box<Apple> appleBox1 = new Box<>();
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addFruit(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange());

        System.out.println("Apple box 1 weight: " + appleBox1.getWeight());
        System.out.println("Apple box 2 weight: " + appleBox2.getWeight());
        System.out.println("Orange box weight: " + orangeBox.getWeight());

        System.out.println("Comparing apple box 1 with apple box 2: " + appleBox1.compare(appleBox2));
        System.out.println("Comparing apple box 1 with orange box: " + appleBox1.compare(orangeBox));

        System.out.println("Transferring fruits from apple box 1 to apple box 2...");
        appleBox1.transferFruitsToAnotherBox(appleBox2);
        System.out.println("Apple box 1 weight: " + appleBox1.getWeight());
        System.out.println("Apple box 2 weight: " + appleBox2.getWeight());
    }
}