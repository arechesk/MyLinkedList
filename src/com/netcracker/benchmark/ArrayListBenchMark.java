package com.netcracker.benchmark;

import com.netcracker.Circle;
import com.netcracker.mylinkedlist.ILinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.CachedLinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.MyLinkedList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ArrayListBenchMark {
    public static void main(String[] args) {
        Random rnd=new Random();
        List<Circle> circlePool=new ArrayList<>();
        for(int i=0;i<10000;i++)circlePool.add(new Circle());
        int[] randInts=new int[10000];
        randInts[0]=0;
        for(int i=1;i<10000;i++)randInts[i]=rnd.nextInt(i);
        int[] op=rnd.ints(10000,0,4).toArray();

        Path file = Paths.get("report.csv");
        List<String> lines = new ArrayList<>();
        lines.add("test, class, N, time");

        long start=System.nanoTime();
        List<Circle> circleArrayList=new ArrayList<>();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
               circleArrayList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("ArrayList vs LinkedList (add), ArrayList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        List<Circle> circleLinkedList=new LinkedList<>();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("ArrayList vs LinkedList (add), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleArrayList.get(randInts[i]);
            }
            lines.add(("ArrayList vs LinkedList (get), ArrayList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.get(randInts[i]);
            }
            lines.add(("ArrayList vs LinkedList (get), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleArrayList.indexOf(circlePool.get(i));
            }
            lines.add(("ArrayList vs LinkedList (indexOf), ArrayList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.indexOf(circlePool.get(i));
            }
            lines.add(("ArrayList vs LinkedList (indexOf), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }
        circleArrayList.addAll(circlePool);
        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                switch (op[i]){
                    case 0:{ circleArrayList.add(randInts[i],circlePool.get(i));break;}
                    case 1: {circleArrayList.get(randInts[i]);break;}
                    case 2: {circleArrayList.indexOf(circlePool.get(i));break;}
                    case 3: {circleArrayList.remove(randInts[i]);break;}
                }
            }
            lines.add(("ArrayList vs LinkedList (mix), ArrayList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }
        circleLinkedList.addAll(circlePool);
        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                switch (op[i]){
                    case 0:{ circleLinkedList.add(randInts[i],circlePool.get(i));break;}
                    case 1: {circleLinkedList.get(randInts[i]);break;}
                    case 2: { circleLinkedList.indexOf(circlePool.get(i));break;}
                    case 3: {circleLinkedList.remove(randInts[i]);break;}
                }
            }
            lines.add(("ArrayList vs LinkedList (mix), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleArrayList.remove(randInts[9999-i]);
            }
            lines.add(("ArrayList vs LinkedList (remove), ArrayList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleArrayList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.remove(randInts[9999-i]);
            }
            lines.add(("ArrayList vs LinkedList (remove), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


        lines.forEach(System.out::println);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
