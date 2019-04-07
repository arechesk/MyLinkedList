package com.netcracker.benchmark;

import com.netcracker.Circle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MapBenchmark {
    public static void main(String[] args) {
        Random rnd=new Random();
        List<Circle> circlePool=new ArrayList<>();
        for(int i=0;i<100000;i++)circlePool.add(new Circle());
        int[] randInts=new int[100000];
        randInts[0]=0;
        for(int i=1;i<100000;i++)randInts[i]=rnd.nextInt(i);
        int[] op=rnd.ints(100000,0,3).toArray();

        Path file = Paths.get("report_map.csv");
        List<String> lines = new ArrayList<>();
        lines.add("test, class, N, time");

        Map<Integer,Circle> hashmap=new HashMap<>();
        Map<Integer,Circle> linkedHashMap=new LinkedHashMap<>();
        Map<Integer,Circle> treeMap=new TreeMap<>();

        long start;

        start=System.nanoTime();
        for(int s=1;s<=10;s++)
        {
            int i=0;
            for(;i<10000;i++){
                hashmap.put(i*s,circlePool.get(i*s));
            }
            lines.add(("add, HashMap,"
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
                linkedHashMap.put(i*s,circlePool.get(i*s));
            }
            lines.add(("add, LinkedHashMap,"
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
                treeMap.put(i*s,circlePool.get(i*s));
            }
            lines.add(("add, TreeMap,"
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
                hashmap.containsKey(i*s);
            }
            lines.add(("contains, HashMap,"
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
                linkedHashMap.containsKey(i*s);
            }
            lines.add(("contains, LinkedHashMap,"
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
                treeMap.containsKey(i*s);
            }
            lines.add(("contains, TreeMap,"
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
                hashmap.remove(i*s);
            }
            lines.add(("remove, HashMap,"
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
                linkedHashMap.remove(i*s);
            }
            lines.add(("remove, LinkedHashMap,"
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
                treeMap.remove(i*s);
            }
            lines.add(("remove, TreeMap,"
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
                    case 0:{hashmap.put(i*s,circlePool.get(i*s));break;}
                    case 1:{hashmap.containsKey(i*s);break;}
                    case 2:{hashmap.remove(i*s);break;}
                }

            }
            lines.add(("mix, HashMap,"
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
                    case 0:{linkedHashMap.put(i*s,circlePool.get(i*s));break;}
                    case 1:{linkedHashMap.containsKey(i*s);break;}
                    case 2:{linkedHashMap.remove(i*s);break;}
                }

            }
            lines.add(("mix, LinkedHashMap,"
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
                    case 0:{treeMap.put(i*s,circlePool.get(i*s));break;}
                    case 1:{treeMap.containsKey(i*s);break;}
                    case 2:{treeMap.remove(i*s);break;}
                }

            }
            lines.add(("mix, TreeMap,"
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
