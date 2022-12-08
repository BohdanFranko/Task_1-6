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
    public void print() {
        System.out.println("------------Goods------------");
        goods.forEach(System.out::println);
    }

}
