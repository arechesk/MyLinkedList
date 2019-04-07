package com.netcracker.benchmark;

import com.netcracker.Circle;
import com.netcracker.mylinkedlist.ILinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.CachedLinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.MyLinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.StupidMyLinkedList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyLinkedListBenchMark {

    public static void main(String[] args) {
        Random rnd=new Random();
        List<Circle> circlePool=new ArrayList<>();
        for(int i=0;i<10000;i++)circlePool.add(new Circle());
        int[] randInts=new int[10000];
        randInts[0]=0;
        for(int i=1;i<10000;i++)randInts[i]=rnd.nextInt(i);
        int[] op=rnd.ints(10000,0,4).toArray();

        Path file = Paths.get("report_mylinkedlist.csv");
        List<String> lines = new ArrayList<>();
        lines.add("test, class, N, time");

        long start=System.nanoTime();
        ILinkedList<Circle> circleMyLinkedList=new MyLinkedList<>();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleMyLinkedList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (add), MyLinkedLis,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        ILinkedList<Circle> circleStupidMyLinkedList=new StupidMyLinkedList<>();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleStupidMyLinkedList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (add), StupidMyLinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        List<Circle> circleLinkedList=new LinkedList<>();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (add), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        ILinkedList<Circle> circleCachedList=new CachedLinkedList<>();
        circleCachedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleCachedList.add(randInts[i],circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (add), circleCachedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleStupidMyLinkedList.get(randInts[i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (get), StupidMyLinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleMyLinkedList.get(randInts[i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (get), MyLinkedLis,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.get(randInts[i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (get), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleCachedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleCachedList.get(randInts[i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (get), circleCachedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleStupidMyLinkedList.indexOf(circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (indexOf), StupidMyLinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleMyLinkedList.indexOf(circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (indexOf), MyLinkedLis,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.indexOf(circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (indexOf), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleCachedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleCachedList.indexOf(circlePool.get(i));
            }
            lines.add(("MyLinkedLis vs LinkedList (indexOf), circleCachedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                switch (op[i]){
                    case 0:{ circleStupidMyLinkedList.add(randInts[i],circlePool.get(i));break;}
                    case 1: {circleStupidMyLinkedList.get(randInts[i]);break;}
                    case 2: {circleStupidMyLinkedList.indexOf(circlePool.get(i));break;}
                    case 3: {circleStupidMyLinkedList.remove(randInts[i]);break;}
                }

            }
            lines.add(("MyLinkedLis vs LinkedList (mix), StupidMyLinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


        circleMyLinkedList.addAll(circlePool);
        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                switch (op[i]){
                    case 0:{ circleMyLinkedList.add(randInts[i],circlePool.get(i));break;}
                    case 1: {circleMyLinkedList.get(randInts[i]);break;}
                    case 2: {circleMyLinkedList.indexOf(circlePool.get(i));break;}
                    case 3: {circleMyLinkedList.remove(randInts[i]);break;}
                }
            }
            lines.add(("MyLinkedLis vs LinkedList (mix), MyLinkedLis,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        circleLinkedList.addAll(circlePool);
        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
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
            lines.add(("MyLinkedLis vs LinkedList (mix), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


        start=System.nanoTime();
        circleCachedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                switch (op[i]){
                    case 0:{ circleCachedList.add(randInts[i],circlePool.get(i));break;}
                    case 1: {circleCachedList.get(randInts[i]);break;}
                    case 2: { circleCachedList.indexOf(circlePool.get(i));break;}
                    case 3: {circleCachedList.remove(randInts[i]);break;}
                }
            }
            lines.add(("MyLinkedLis vs LinkedList (mix), circleCachedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


        start=System.nanoTime();

        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleMyLinkedList.remove(randInts[9999-i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (remove), MyLinkedLis,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleStupidMyLinkedList.remove(randInts[9999-i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (remove), StupidMyLinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }

        start=System.nanoTime();
        circleMyLinkedList.add(circlePool.get(0));
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                circleLinkedList.remove(randInts[9999-i]);
            }
            lines.add(("MyLinkedLis vs LinkedList (remove), LinkedList,"
                    +(i*s)
                    +","
                    +(System.nanoTime()-start
            )));
            i=0;
        }


//        start=System.nanoTime();
//        circleCachedList.add(circlePool.get(0));
//        for(int s=1;s<=10;s++)
//        {
//            int i=0;
//            for(;i<10000;i++){
//                circleCachedList.remove(randInts[9999-i]);
//            }
//            lines.add(("MyLinkedLis vs LinkedList (remove), circleCachedList,"
//                    +(i*s)
//                    +","
//                    +(System.nanoTime()-start
//            )));
//            i=0;
//        }


        lines.forEach(System.out::println);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
