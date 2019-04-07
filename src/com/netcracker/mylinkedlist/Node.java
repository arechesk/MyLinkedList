package com.netcracker.mylinkedlist;

public class Node<E>  {
    public E element;
    public Node<E> nextNode;

    public Node(E element) {
        this.element = element;
    }
}
