
import java.util.ArrayList;
import java.util.Arrays;



/**
 * Java. Level 3. Lesson 1.
 * @version 02.03.2019
 */


/*
1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
 нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока
 - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
 в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
  сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
 фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
  а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */


public class lesson1_generic {

    public static void main(String[] args) {

        //Задание №1

        String[] stringArr1 = {"первый", "второй", "третий", "четвертый", "пятый"}; //Массив типа string
        String[] stringArr2 = {"ome", "two", "three", "four", "five"};
        Integer[] integerArr = {1,2,3,4,5}; //Маcсив типа integer

        System.out.println("Task 1\n");

        System.out.println ("Input Data");

        //Выводим в консоль исходные массивы
        System.out.println("Integer array: " + Arrays.toString(integerArr));
        System.out.println("String1 array: " + Arrays.toString(stringArr1));
        System.out.println("String2 array: " + Arrays.toString(stringArr2));
        System.out.println ("\nResult");

        //Переносим элементы
        try {
            swapTwoElements(integerArr, 1, 3); //запускаем метод, который меняет местами
            swapTwoElements(stringArr1, 0, 2);
            swapTwoElements(stringArr2, 0, 4);
        } catch (PositionException e) {
            e.printStackTrace();
        }
        System.out.println("The result of replacement Integer array (1<-->3): " + Arrays.toString(integerArr));
        System.out.println("The result of replacement String1 array (0<-->2): " + Arrays.toString(stringArr1));
        System.out.println("The result of replacement String2 array (0<-->4): " + Arrays.toString(stringArr2));

        //Задание №2

        System.out.println("\nTask2\n");
        //Преобразуем массив stringArr1 в ArrayList
        asListConvert (stringArr1);

        //Задание №3

        //Создаем экземляры Box с апельсинами
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        //Создаем экземляры Box с яблоками
        Box<Apple> appleBox3 = new Box<>();
        Box<Apple> appleBox4 = new Box<>();

        System.out.println("\nTask3\n");

        //Наполняем коробки

        System.out.println("Box 1: 20 oranges (1.5f per one)\nBox 2: 15 oranges (1.5f per one)\n" +
                "Box 3: 30 apples (1.0f per one)\nBox 4: 10 apples (1.0f per one)\n");

        orangeBox1.addFruits(new Orange(),20);
        orangeBox2.addFruits(new Orange(),15);

        appleBox3.addFruits(new Apple(),30);
        appleBox4.addFruits(new Apple(),10);

        System.out.println("Weights (getWeight):\n");
        System.out.println("Orange Box 1: " +  orangeBox1.getWeight());
        System.out.println("Orange Box 2: " + orangeBox2.getWeight());
        System.out.println("Apple Box 3: " + appleBox3.getWeight());
        System.out.println("Apple Box 4: " + appleBox4.getWeight());

        //Сравниваем массы
        System.out.println("\nCompare (compare):");
        System.out.println("Box 1 has identical weight with Box 3: " + orangeBox1.compare(appleBox3));
        System.out.println("Box 2 has identical weight with Box 4: " + orangeBox1.compare(appleBox4));

        //Пересыпаем апельсины

        System.out.println("\nPour 1-->2 and 3-->4 (pourTheBox)");
        orangeBox1.pourTheBox(orangeBox2);
        appleBox3.pourTheBox(appleBox4);
        System.out.println("New Weights (getWeight):\n");
        System.out.println("Orange Box 1: " +  orangeBox1.getWeight());
        System.out.println("Orange Box 2: " + orangeBox2.getWeight());
        System.out.println("Apple Box 3: " + appleBox3.getWeight());
        System.out.println("Apple Box 4: " + appleBox4.getWeight());

    }


    /**
     * Метод который меняет два элемента массива местами
     * array - входящий массив
     * firstNumPosition - позиция 1
     * secondNumPosition - позиция 2
     */

    private static void swapTwoElements(Object[] array, int firstNumPosition, int secondNumPosition)
            throws PositionException {
        if (firstNumPosition < 0 || firstNumPosition > array.length ||
                secondNumPosition < 0 || secondNumPosition > array.length || firstNumPosition == secondNumPosition) {
            throw new PositionException("Неправильно указаны позиции элементов");
        }

        //Создаем эл. типа Object
        Object tempArr = array[firstNumPosition];

        //Заменяем "первый" элемент на второй
        array[firstNumPosition] = array[secondNumPosition];

        //Записываем во "второй" элемент "временный" элемент типа Object
        array[secondNumPosition] = tempArr;
    }

    /**
     * Метод который преобразует массив в ArrayList
     * arr - входящий массив
     */

    public static <T> void asListConvert(T[]arr){

        ArrayList<T> listTransform = new ArrayList<>(Arrays.asList(arr));
        System.out.println("The result of the conversion: "+listTransform +
                "\n================================");
    }

}


