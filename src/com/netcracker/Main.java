package com.netcracker;

import com.netcracker.benchmark.ArrayListBenchMark;
import com.netcracker.benchmark.MapBenchmark;
import com.netcracker.benchmark.MyLinkedListBenchMark;
import com.netcracker.benchmark.SetBenchmark;
import com.netcracker.mylinkedlist.ILinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.CachedLinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.MyLinkedList;
import com.netcracker.mylinkedlist.ILinkedListImpl.StupidMyLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayListBenchMark.main(null);
        MapBenchmark.main(null);
        MyLinkedListBenchMark.main(null);
        SetBenchmark.main(null);

       


    }
}
