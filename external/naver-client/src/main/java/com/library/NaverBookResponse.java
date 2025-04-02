package com.library;

import lombok.Getter;

import java.util.List;

@Getter
public class NaverBookResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
}
