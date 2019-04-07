package com.netcracker.mylinkedlist.ILinkedListImpl;

import com.netcracker.mylinkedlist.ILinkedList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CachedLinkedList<E> implements ILinkedList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private Node<E> midNode;

    private int size=0;
    private int midIndex;

    private Map<E,Integer> cache=new HashMap<>();

    public CachedLinkedList() { }

    public CachedLinkedList(Collection<? extends E> c){
        this();
        addAll(c);

    }
    public void addAll(Collection<? extends E> c){
        for(E e:c) add(e);
    }


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
        if(!cache.containsKey(element)){cache.put(element,size);}
        size++;

    }
    void resetMidNode(){
        midNode=node(size>>1);
        midIndex=(size>>1);
    }

    @Override
    public void add(int index, E element) {
        if(midIndex!=size/2)
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
        if(!cache.containsKey(element)){cache.put(element,index);}

            for(Map.Entry<E,Integer> entry: cache.entrySet()){
                if(entry.getValue()>=index)entry.setValue(entry.getValue()+1);;
            }


    }

    @Override
    public void clear() {
        firstNode=midNode = lastNode = null;
        size = 0;
        midIndex=0;
        cache=new HashMap<>();
    }

    @Override
    public E get(int index) {
       // if(cache.containsValue(index)){}
        if(index>=size||index<0){throw new IndexOutOfBoundsException();}
        Node<E> res=node(index);
        return res.element;
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
     int indexOfOld(E element,int frst) {

      try{  Node<E> cur=node(frst);
        for(int i=frst;i<(size-frst);i++){
            if(cur.element.equals(element))return i;
            cur=cur.nextNode;
        }}
        catch (NullPointerException e){
          e.printStackTrace();
        }
        return -1;
    }
    @Override
    public int indexOf(E element) {
        return cache.getOrDefault(element,-1);

    }

    @Override
    public E remove(int index) {
        if(index>=size){throw new IndexOutOfBoundsException();}
        Node<E> res=firstNode;
        if(index==0){
            firstNode=firstNode.nextNode;

        }else{


            Node<E> head=node(index).prevNode;
            res=head.nextNode;
            head.nextNode=(res.nextNode);
            head.prevNode=res.prevNode;
            res.nextNode.prevNode=head;
            res.prevNode.nextNode=head.nextNode;

        }
        size--;
        if(midIndex>index)midIndex--;
        if(midIndex==index){midNode=midNode.nextNode;midIndex--;}
        if(cache.containsKey(res.element))
        {
            int i=indexOfOld(res.element,index);
            if(i==-1)cache.remove(res.element);
            else cache.put(res.element,i);
        }
        for(Map.Entry<E,Integer> entry: cache.entrySet()){
            if(entry.getValue()>index)entry.setValue(entry.getValue()-1);
        }

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
        public Node<E> prevNode;


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
            nextItem=nextItem.nextNode;
            return currentItem.element;
        }

        public MyIterator(Node<E> Item, int size) {
            this.size=size;
            this.currentItem=this.nextItem = Item;
        }
    }
}
