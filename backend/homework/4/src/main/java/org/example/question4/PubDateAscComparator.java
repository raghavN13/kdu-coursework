package org.example.question4;

import java.util.Comparator;

class PubDateAscComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        if (((Book)o1).getYear() == ((Book)o2).getYear()) {
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else if (((Book)o1).getYear() < ((Book)o2).getYear()) {
            return -1;
        } else {
            return 1;
        }
    }
}
