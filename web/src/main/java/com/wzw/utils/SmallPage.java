package com.wzw.utils;


public class SmallPage {
    /**
     * @description 当前页，总页数
     * @param  
     * @throws 
     * @return 
     */
    
    public static int[] minAndMax(int currentPage,int allPage){
        int [] pages = new int[2];

        if (allPage <= 5){
            pages[0] = 1;
            pages[1] = allPage;
            return pages;
        }
        if(currentPage <= 3){
            pages[0] = 1;
            pages[1] = 5;
            return pages;
        }
        if (currentPage >= allPage - 2) {
            pages[0] = allPage - 4;
            pages[1] = allPage;
            return pages;
        }
        pages[0] = currentPage - 2;
        pages[1] = currentPage + 2;
        return pages;
    }
}
