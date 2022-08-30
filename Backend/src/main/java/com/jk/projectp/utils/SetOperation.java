package com.jk.projectp.utils;

import cn.hutool.core.lang.Pair;

import java.util.HashSet;
import java.util.Set;

public class SetOperation {
    /*
    *   Key: add set
    *   Value: delete set
    *
    * */
    public static <T> Pair<Set<T>, Set<T>> getAddAndDeleteSet(Set<T> originalSet, Set<T> newSet) {
        Set<T> addSet = new HashSet<>(newSet);
        Set<T> deleteSet = new HashSet<>(originalSet);
        addSet.removeAll(originalSet);
        deleteSet.removeAll(newSet);
        return new Pair<>(addSet, deleteSet);
    }
}
