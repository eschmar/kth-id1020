package io.eschmann;

import java.util.Comparator;

/**
 * Created by eschmar on 11/12/16.
 */
public class Query implements Comparable<String> {
    public Comparable<String> left;
    public String operator;
    public Comparable<String> right;
    public String cacheId;
    public boolean isSingle = true;
    private int format = 0;

    public static final String OPERATORS = "+|-";
    public static final String COMMUTATIVE_OPERATORS = "+|";

    public static final int FORMAT_INFIX = 0;
    public static final int FORMAT_PREFIX = 1;
    public static final int FORMAT_POSTFIX = 2;

    public Query(Comparable<String> left) {
        this.left = left;
    }

    public Query(Comparable<String> left, String operator, Comparable<String> right) {
        if (operator.length() != 1 || !OPERATORS.contains(operator)) {
            throw new IllegalArgumentException("Invalid operator!");
        }

        if (COMMUTATIVE_OPERATORS.contains(operator) && left.toString().compareTo(right.toString()) >= 1) {
            Comparable<String> temp = left;
            left = right;
            right = temp;
        }

        this.left = left;
        this.operator = operator;
        this.right = right;
        this.cacheId = null;
        this.isSingle = false;
    }

    public void setFormat(int format) {
        if (format != FORMAT_PREFIX && format != FORMAT_INFIX && format != FORMAT_POSTFIX) {
            throw new IllegalArgumentException("Illegal query format!");
        }

        this.format = format;
    }

    @Override
    public String toString() {
        if (this.isSingle) {
            return this.left.toString();
        }

        if (this.left instanceof Query) {
            ((Query) this.left).setFormat(this.format);
        }

        if (this.right instanceof Query) {
            ((Query) this.right).setFormat(this.format);
        }

        if (this.format == FORMAT_PREFIX) {
            return this.wrapTriplet(this.operator, this.left, this.right);
        }else if (this.format == FORMAT_POSTFIX) {
            return this.wrapTriplet(this.left, this.right, this.operator);
        }

        return this.wrapTriplet(this.left, this.operator, this.right);
    }

    private String wrapTriplet(Comparable<String> left, Comparable<String> middle, Comparable<String> right) {
        return "(" + left.toString() + " " + middle.toString() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof String) {
            String compare = (String) o;
            return this.toString().equals(compare);
        }

        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public int compareTo(String o) {
        return this.toString().compareTo(o);
    }
}
