package io.eschmann;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eschmar on 11/12/16.
 */
public class SetHelper {
    public static ArrayList<DocumentWrapper> union(ArrayList<DocumentWrapper> a, ArrayList<DocumentWrapper> b) {
        ArrayList<DocumentWrapper> result = new ArrayList<DocumentWrapper>();

        for (DocumentWrapper wrapper : a) {
            result.add(wrapper);
        }

        for (DocumentWrapper wrapper : b) {
            int pos = Collections.binarySearch(result, wrapper);

            if (pos < 0) {
                result.add(-pos-1, wrapper);
                continue;
            }

            DocumentWrapper temp = (DocumentWrapper) result.get(pos);
            mergeElements(temp, wrapper);
        }

        return result;
    }

    public static ArrayList<DocumentWrapper> intersection(ArrayList<DocumentWrapper> a, ArrayList<DocumentWrapper> b) {
        ArrayList<DocumentWrapper> result = new ArrayList<DocumentWrapper>();

        for (DocumentWrapper wrapper : b) {
            int pos = Collections.binarySearch(a, wrapper);

            if (pos < 0) {
                continue;
            }

            DocumentWrapper temp = (DocumentWrapper) a.get(pos);
            mergeElements(temp, wrapper);

            result.add(temp);
        }

        return result;
    }

    public static ArrayList<DocumentWrapper> difference(ArrayList<DocumentWrapper> a, ArrayList<DocumentWrapper> b) {
        ArrayList<DocumentWrapper> result = new ArrayList<DocumentWrapper>();

        for (DocumentWrapper wrapper : a) {
            int pos = Collections.binarySearch(b, wrapper);

            if (pos < 0) {
                result.add(wrapper);
            }
        }

        return result;
    }

    private static void mergeElements(DocumentWrapper a, DocumentWrapper b) {
        a.occurrences += b.occurrences;
        if (b.firstOccurrence < a.firstOccurrence) {
            a.firstOccurrence = b.firstOccurrence;
        }
    }
}
