package com.ironhack.opportunity.utils;

import java.util.List;

public class Math {

    public static Double median(List<Double> orderedList){
        if (orderedList.size()>0){
            // Even-sized lists
            if (orderedList.size()%2==0){
                Double first = orderedList.get((orderedList.size()/2)-1);
                Double second = orderedList.get(orderedList.size()/2);
                return (first+second)/2.0;
            // Odd-sized lists
            }else{
                Integer index = (orderedList.size()-1)/2;
                return orderedList.get(index);
            }
        }else{
            throw new IllegalArgumentException("There are no elements to calculate the median");
        }

    }
}
