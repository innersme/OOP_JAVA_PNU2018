package pnu.edu.editor;

import java.util.*;
import pnu.edu.shape.*;



public class Editor {

    public static void sortList(
            final List<AreaComputable> comparableList, final SortKind sortKind) {
        if (sortKind == SortKind.ASCENDING)
        {
            for (int i = 0; i < comparableList.size() - 1; i++){
                for (int j = i + 1; j < comparableList.size() ; j++){
                    if (comparableList.get(i).compareTo(comparableList.get(j)) > 0) {
                        AreaComputable temp = comparableList.get(j);
                        comparableList.set(j,comparableList.get(i));
                        comparableList.set(i,temp);
                    }
                }
            }
        }

        if (sortKind == SortKind.DESCENDING){
            for (int i = 0; i < comparableList.size() - 1; i++){
                for (int j = i + 1; j < comparableList.size() ; j++){
                    if (comparableList.get(i).compareTo(comparableList.get(j)) < 0) {
                        AreaComputable temp = comparableList.get(j);
                        comparableList.set(j,comparableList.get(i));
                        comparableList.set(i,temp);
                    }
                }
            }
        }
    }
};
