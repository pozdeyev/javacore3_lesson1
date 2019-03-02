import java.util.ArrayList;

//Класс Box с обобщением T расширяющим Fruit
public class Box <T extends Fruit> {
    private ArrayList<T> box = new ArrayList<>();


//метод вычисляющий массу коробки
    public float getWeight(){
        float weight = 0.0f;
        for(T o : box) weight += o.getWeight();
        return weight;
    }


    //сравнение ящиков
    public boolean compare(Box anotherBox) {
    //Если масса другого ящика совпадает возвращаем true, если нет - false
        if(getWeight() == anotherBox.getWeight()) return true;
        return false;
    }

    //пересыпаем из ящика в другой
    public void pourTheBox(Box <T>anotherBox){
        anotherBox.box.addAll(box);
        box.clear();
    }

// добавляем фрукты

    public void addFruits(T fruit, int am) {
        for (int i = 0; i < am; i++) {
            box.add(fruit);
        }
    }

}


