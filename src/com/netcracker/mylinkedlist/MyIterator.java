package com.netcracker.mylinkedlist;

import java.util.Iterator;

public class MyIterator<E> implements Iterator<E> {
    private Node<E> currentItem;
    private Node<E> nextItem;
    private int index=0;
    private int size;


    @Override
    public boolean hasNext() {
        return index<size;
    }

    @Override
    public E next() {
        index++;
        currentItem=nextItem;
        nextItem=nextItem.nextNode;
        return currentItem.element;
    }

    public MyIterator(Node<E> Item,int size) {
        this.size=size;
        this.currentItem=this.nextItem = Item;
    }
}
