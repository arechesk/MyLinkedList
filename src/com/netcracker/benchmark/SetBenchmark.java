package com.netcracker.benchmark;

import com.netcracker.Circle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SetBenchmark {
    public static void main(String[] args) {
        Random rnd=new Random();
        List<Circle> circlePool=new ArrayList<>();
        for(int i=0;i<100000;i++)circlePool.add(new Circle());
        int[] randInts=new int[100000];
        randInts[0]=0;
        for(int i=1;i<100000;i++)randInts[i]=rnd.nextInt(i);
        int[] op=rnd.ints(100000,0,3).toArray();

        Path file = Paths.get("report_set.csv");
        List<String> lines = new ArrayList<>();
        lines.add("test, class, N, time");

        Set<Circle> hashset=new HashSet<>();
        Set<Circle> linkedHashSet=new LinkedHashSet<>();
        Set<Circle> treeSet=new TreeSet<>();

        long start;

        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                hashset.add(circlePool.get(i*s));
            }
            lines.add(("add, HashSet,"
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
                linkedHashSet.add(circlePool.get(i*s));
            }
            lines.add(("add, LinkedHashSet,"
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
                treeSet.add(circlePool.get(i*s));
            }
            lines.add(("add, TreeSet,"
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
                hashset.contains(circlePool.get(i*s));
            }
            lines.add(("contains, HashSet,"
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
                linkedHashSet.contains(circlePool.get(i*s));
            }
            lines.add(("contains, LinkedHashSet,"
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
                treeSet.contains(circlePool.get(i*s));
            }
            lines.add(("contains, TreeSet,"
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
                hashset.remove(circlePool.get(i*s));
            }
            lines.add(("remove, HashSet,"
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
                linkedHashSet.remove(circlePool.get(i*s));
            }
            lines.add(("remove, LinkedHashSet,"
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
                treeSet.remove(circlePool.get(i*s));
            }
            lines.add(("remove, TreeSet,"
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
                switch (op[i*s]){
                    case 0:{hashset.add(circlePool.get(i*s));break;}
                    case 1:{hashset.contains(circlePool.get(i*s));break;}
                    case 2:{hashset.remove(circlePool.get(i*s));break;}
                }

            }
            lines.add(("mix, HashSet,"
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
                switch (op[i*s]){
                    case 0:{linkedHashSet.add(circlePool.get(i*s));break;}
                    case 1:{linkedHashSet.contains(circlePool.get(i*s));break;}
                    case 2:{linkedHashSet.remove(circlePool.get(i*s));break;}
                }

            }
            lines.add(("mix, LinkedHashSet,"
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
                switch (op[i*s]){
                    case 0:{treeSet.add(circlePool.get(i*s));break;}
                    case 1:{treeSet.contains(circlePool.get(i*s));break;}
                    case 2:{treeSet.remove(circlePool.get(i*s));break;}
                }

            }
            lines.add(("mix, TreeSet,"
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
