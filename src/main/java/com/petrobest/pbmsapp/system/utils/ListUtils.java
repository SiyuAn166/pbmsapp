package com.petrobest.pbmsapp.system.utils;

import java.util.Collection;

public class ListUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null) || (collection.size() <= 0);
    }
}
