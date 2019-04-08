package com.netcracker.mylinkedlist.ILinkedListImpl;


import com.netcracker.mylinkedlist.ILinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyLinkedList<E> implements ILinkedList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private Node<E> midNode;
    private int size=0;
    private int midIndex=0;

    public MyLinkedList() { }

    public MyLinkedList(Collection<? extends E> c){
        this();
        addAll(c);

    }
    public void addAll(Collection<? extends E> c){
        for(E e:c) add(e);
    }



    Node<E> node(int index){
        if(index==0)return firstNode;
        if(index==size-1)return lastNode;
        if(index==midIndex)return midNode;
        Node<E> cur;
        if(index>midIndex){
           if(index<(midIndex+midIndex/2)){
               cur=midNode;
               for(int i=midIndex;i<index;i++)
                   cur=cur.nextNode;
           }

               else {cur=lastNode;
           for(int i=size-1;i>index;i--)
               cur=cur.prevNode;
            }}

        else{
            if(index>=(midIndex/2)){
                cur=midNode;
                for(int i=midIndex;i>index;i--)
                    cur=cur.prevNode;
            }else

            {cur=firstNode;
            for(int i=0;i<index;i++)
                cur=cur.nextNode;
            }}

        return cur;
    }

    @Override
    public void add(E element) {
        final Node<E> newNode = new Node<>(element);
        if (firstNode != null)
        {
            lastNode.nextNode = newNode;
            lastNode.nextNode.prevNode=lastNode;
        }
        else
        {
            firstNode =midNode= newNode;

        }
        lastNode=newNode;
        size++;


    }
    void resetMidNode(){
        if(midIndex==(size/2))return;
            midNode=node(size>>1);
            midIndex=(size>>1);
    }

    @Override
    public void add(int index, E element) {
        //if(index>255&&Math.log(size)/Math.log(2)%1==0)resetMidNode();
//        if(midIndex!=size/2)
            resetMidNode();
        if(index==(size)){add(element);return;}
        if(index>=size){throw new IndexOutOfBoundsException();}
        Node<E> tail=firstNode;
        if(index==0){

            firstNode=new Node<>(element);
            firstNode.nextNode=(tail);
            tail.prevNode=firstNode;
        }
        else{
            Node<E> head=node(index).prevNode;
            tail=head.nextNode;
            head.nextNode=(new Node<>(element));
            head.nextNode.nextNode=(tail);
            tail.prevNode=head.nextNode;
            head.nextNode.prevNode=head;

        }
        size++;
        if(midIndex>=index)midIndex++;
       // resetMidNode();

    }

    @Override
    public void clear() {
        firstNode = lastNode=midNode = null;
        size = 0;
        midIndex=0;
    }

    @Override
    public E get(int index) {
        if(index>=size||index<0){throw new IndexOutOfBoundsException();}
        return node(index).element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> cur=firstNode;
        for(int i=0;i<size;i++){
            if(cur.element.equals(element))return i;
            cur=cur.nextNode;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        resetMidNode();
       if(index>=size|| index<0){return null;}
        Node<E> res=firstNode;
        if(index==size-1){res=node(index);node(index).prevNode.nextNode=null;}
        else{
        if(index==0){
            firstNode=firstNode.nextNode;

        }else{
            Node<E> head=node(index-1);
            res=head.nextNode;
            head.nextNode=(res.nextNode);
            head.prevNode=res.prevNode;
            res.nextNode.prevNode=head;
            res.prevNode.nextNode=head.nextNode;

        }}
        size--;
       if(midIndex>index)midIndex--;
        else
       if(midIndex==index){midNode=midNode.nextNode;midIndex--;}
        return res.element;


    }

    @Override
    public E set(int index, E element) {
        if(index>size){throw new IndexOutOfBoundsException();}
        Node<E> res=node(index);
        E old=res.element;
        res.element=(element);
        return old;
    }

    @Override
    public int size() {
        return size;
    }


        @Override
        public E[] toArray() {

            if(size==0) return null;
            E[] arr;
            arr = (E[])java.lang.reflect.Array.newInstance(
                    firstNode.element.getClass().getComponentType(), size);
            Node<E> next = firstNode;
            for(int i=0;i<size;i++)
            {
                arr[i]=next.element;
                next=next.nextNode;

            }
            return arr;
        }




    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>(firstNode,size);
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
    static public class Node<E>  {
        public E element;
        public Node<E> nextNode;
        public  Node<E> prevNode;

        public Node(E element) {
            this.element = element;
        }


    }
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
           if(nextItem!=null) {nextItem=nextItem.nextNode;
            return currentItem.element;}
            return null;
        }

        public MyIterator(Node<E> Item, int size) {
            this.size=size;
            this.currentItem=this.nextItem = Item;
        }
    }
}
