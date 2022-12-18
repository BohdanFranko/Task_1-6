package com.epam.store;

import com.epam.transport.Automobile;

import java.util.ArrayList;
import java.util.List;

public class ListOfGoods {
    private List<Automobile> goods;

    public List<Automobile> getGoods() {
        return goods;
    }

    public ListOfGoods() {
        goods = new ArrayList<>();
    }

    public ListOfGoods(List<Automobile> list) {
        goods = list;
    }

    public boolean add(Automobile automobile) {
        return goods.add(automobile);
    }

    public boolean checkId(int productId) {
        for (Automobile good : goods) {
            if (good.getId() == productId) {
                return true;
            }
        }
        return false;
    }

    public Automobile getAutoById(int productId) {
        for (Automobile good : goods) {
            if (good.getId() == productId) {
                return good;
            }
        }
        return null;
    }

    public void print() {
        System.out.println("------------Goods------------");
        goods.forEach(System.out::println);
    }

}
