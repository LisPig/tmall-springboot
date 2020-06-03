package com.example.tmall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator<T> {
    Page<T> pageFromJPA;//传递出来的分页对象
    int navigatePages;//分页的时候，如果总页数比较多，那么显示出来的分页超链一个有几个

    int totalPages;//总页面数

    int number;//第几页

    long totalElements;//总共多少条数据

    int size;//一页最多多少天数据

    int numberOfElements;//当前页面有多少条数据

    List<T> content;//数据集合

    boolean isHasContent;//是否有数据

    boolean first;//是否是首页

    boolean last;//是否是末页

    boolean isHasNext;//是否有下一页

    boolean isHasPrevious;//是否有上一页

    int[] navigatepageNums;//分页的时候，如果总页数比较多，那么显示出来的分页链一个有几个

    public Page4Navigator(){
        //这个空的分页是为了redis从json格式转换为page4Navigator对象而专门提供的；
    }

    public Page4Navigator(Page<T> pageFromJPA,int navigatePages){
        this.pageFromJPA = pageFromJPA;
        this.navigatePages = navigatePages;

        totalPages = pageFromJPA.getTotalPages();
        number = pageFromJPA.getNumber();
        totalElements = pageFromJPA.getTotalElements();
        size = pageFromJPA.getSize();
        numberOfElements = pageFromJPA.getNumberOfElements();
        content = pageFromJPA.getContent();
        isHasContent = pageFromJPA.hasContent();
        first = pageFromJPA.isFirst();
        last = pageFromJPA.isLast();
        isHasNext = pageFromJPA.hasNext();
        isHasPrevious = pageFromJPA.hasPrevious();
        calcNavigatepageNums();
    }

    private void calcNavigatepageNums() {
        int navigatepageNums[];
        int totalPages = getTotalPages();
        int num = getNumber();
        //当总页数小于或等于导航页码数时
        if(totalPages <= navigatePages){
            navigatepageNums = new int[totalPages];
            for(int i=0;i<totalPages;i++){
                navigatepageNums[i] = i+1;
            }
        }else{
            //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = num - navigatePages / 2;
            int endNum = num + navigatePages/2;
            if(startNum<1){
                startNum = 1;
                //最前navigatePages页
                for(int i = 0;i<navigatePages;i++){
                    navigatepageNums[i] = startNum++;
                }
            }else if(endNum > totalPages){
                endNum = totalPages;
                //最后navigatePages
                for(int i = navigatePages-1;i>=0;i--){
                    navigatepageNums[i] = endNum--;
                }
            }else{
                //所有中间页
                for(int i = 0;i<navigatePages;i++){
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }


    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
