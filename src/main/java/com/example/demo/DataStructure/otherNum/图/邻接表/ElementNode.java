package com.example.demo.DataStructure.otherNum.图.邻接表;

/**
 * @author idea
 * @data 2019/8/25
 */

public class ElementNode {

    private String value;

    private ElementNode next;

    public ElementNode(String value, ElementNode next) {
        this.value = value;
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public ElementNode setValue(String value) {
        this.value = value;
        return this;
    }

    public ElementNode getNext() {
        return next;
    }

    public ElementNode setNext(ElementNode next) {
        this.next = next;
        return this;
    }
}
