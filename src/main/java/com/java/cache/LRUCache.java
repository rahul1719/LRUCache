package com.java.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
     
    private DoublyLinkedList pageList;
    private Map<Integer, MyNode> pageMap;
    private final int cacheSize;
     
    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        pageList = new DoublyLinkedList(cacheSize);
        pageMap = new HashMap<Integer, MyNode>();
    }
     
    public void accessPage(int pageNumber) {
        MyNode pageNode = null;
        if(pageMap.containsKey(pageNumber)) {
            // If page is present in the cache, move the page to the start of list
            pageNode = pageMap.get(pageNumber);
            pageList.movePageToHead(pageNode);
        } else {
            // If the page is not present in the cache, add the page to the cache
            if(pageList.getCurrSize() == pageList.getSize()) {
                // If cache is full, we will remove the tail from the cache pageList
                // Remove it from map too
                pageMap.remove(pageList.getTail().getPageNumber());
            }
            pageNode = pageList.addPageToList(pageNumber);
            pageMap.put(pageNumber, pageNode);
        }
    }
     
    public void printCacheState() {
        pageList.printList();
        System.out.println();
    }
 
    public static void main(String[] args) {
        int cacheSize = 4;
        LRUCache cache = new LRUCache(cacheSize);
        cache.accessPage(40);
        cache.printCacheState();
        cache.accessPage(20);
        cache.printCacheState();
        cache.accessPage(10);
        cache.printCacheState();
        cache.accessPage(10);
        cache.printCacheState();
        cache.accessPage(40);
        cache.printCacheState();
        cache.accessPage(30);
        cache.printCacheState();
        cache.accessPage(70);
        cache.printCacheState();
        cache.accessPage(80);
        cache.printCacheState();
        cache.accessPage(3);
        cache.printCacheState();
    }
}

class DoublyLinkedList {
     
    private final int size;
    private int currSize;
    private MyNode head;
    private MyNode tail;
 
    public DoublyLinkedList(int size) {
        this.size = size;
        currSize = 0;
    }
 
    public MyNode getTail() {
        return tail;
    }
 
    public void printList() {
        if(head == null) {
            return;
        }
        MyNode tmp = head;
        while(tmp != null) {
            System.out.print(tmp);
            tmp = tmp.getNext();
        }
    }
 
    public MyNode addPageToList(int pageNumber) {
        MyNode pageNode = new MyNode(pageNumber);
        if(head == null) {
            head = pageNode;
            tail = pageNode; 
            currSize = 1;
            return pageNode;
        } else if(currSize < size) {
            currSize++;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        pageNode.setNext(head);
        head.setPrev(pageNode);
        head = pageNode;
        return pageNode;
    }
 
    public void movePageToHead(MyNode pageNode) {
        if(pageNode == null || pageNode == head) {
            return;
        }
 
        if(pageNode == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        MyNode prev = pageNode.getPrev();
        MyNode next = pageNode.getNext();
        prev.setNext(next);
 
        if(next != null) {
            next.setPrev(prev);
        }
 
        pageNode.setPrev(null);
        pageNode.setNext(head);
        head.setPrev(pageNode);
        head = pageNode;    
    }
 
    public int getCurrSize() {
        return currSize;
    }
 
    public void setCurrSize(int currSize) {
        this.currSize = currSize;
    }
 
    public MyNode getHead() {
        return head;
    }
 
    public void setHead(MyNode head) {
        this.head = head;
    }
 
    public int getSize() {
        return size;
    }   
}

class MyNode {
     
    private int pageNumber;
    private MyNode prev;
    private MyNode next;
     
    public MyNode(int pageNumber) {
        this.pageNumber = pageNumber;
    }
 
    public int getPageNumber() {
        return pageNumber;
    }
 
    public void setPageNumber(int data) {
        this.pageNumber = data;
    }
     
    public MyNode getPrev() {
        return prev;
    }
 
    public void setPrev(MyNode prev) {
        this.prev = prev;
    }
 
    public MyNode getNext() {
        return next;
    }
 
    public void setNext(MyNode next) {
        this.next = next;
    }
     
    public String toString() {
        return pageNumber + "  ";
    }
}