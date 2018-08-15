package com.pphh.demo.po;

import java.util.List;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 8/15/2018
 */
public class Page<T> {

    int index;
    int size;
    long totoalPages;
    long totoalElements;
    List<T> content;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotoalPages() {
        return totoalPages;
    }

    public void setTotoalPages(long totoalPages) {
        this.totoalPages = totoalPages;
    }

    public long getTotoalElements() {
        return totoalElements;
    }

    public void setTotoalElements(long totoalElements) {
        this.totoalElements = totoalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
