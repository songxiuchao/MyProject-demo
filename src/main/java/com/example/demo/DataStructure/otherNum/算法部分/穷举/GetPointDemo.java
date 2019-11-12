package com.example.demo.DataStructure.otherNum.算法部分.穷举;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane,
 * find the maximum number of points that
 * lie on the same straight line.
 *
 * @author idea
 * @data 2019/6/9
 */
public class GetPointDemo {

    public int maxPoints(Point[] points) {

        int n = points.length;
        if (n < 2) {
            return n;
        }
        int result = 0;
        for (Point point : points) {
            Map<Double, Integer> map = new HashMap<>();
            //点重复
            int dup = 1;
            //点在同一垂直线上 （没有斜率可以计算，因此需要特殊处理）
            int vtl = 0;
            for (Point innerPointer : points) {
                if (point == innerPointer) {
                    continue;
                }
                if (point.x == innerPointer.x) {
                    if (point.y == innerPointer.y) {
                        dup++;
                    } else {
                        vtl++;
                    }
                } else {
                    double x1 = point.x - innerPointer.x;
                    double y1 = point.y - innerPointer.y;
                    double rake = y1 / x1;
                    if (map.get(rake) == null) {
                        map.put(rake, 1);
                    } else {
                        map.put(rake, map.get(rake) + 1);
                    }
                }
            }
            int max = vtl;
            for (Double key : map.keySet()) {
                max = Math.max(max, map.get(key));
            }
            result = Math.max(max, max + dup);
        }
        return result;
    }

    public int maxPoints2(Point[] points) {
        int n = points.length;
        if (n < 2) {
            return n;
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            // 分别统计与点i重合以及垂直的点的个数
            int dup = 1, vtl = 0;
            Map<Float, Integer> map = new HashMap<>();
            Point a = points[i];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                Point b = points[j];
                if (a.x == b.x) {
                    if (a.y == b.y) dup++;
                    else vtl++;
                } else {
                    float k = (float) (a.y - b.y) / (a.x - b.x);
                    if (map.get(k) == null) map.put(k, 1);
                    else map.put(k, map.get(k) + 1);
                }
            }

            int max = vtl;
            for (float k : map.keySet()) {
                max = Math.max(max, map.get(k));
            }
            ret = Math.max(ret, max + dup);
        }
        return ret;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 3);
        Point p4 = new Point(2, 1);
        Point p5 = new Point(3, 1);
        Point p6 = new Point(4, 1);
        Point p7 = new Point(5, 1);
        Point[] points = {p1, p2, p3, p4, p5, p6, p7};
        GetPointDemo gp = new GetPointDemo();
        int result = gp.maxPoints(points);
        System.out.println(result);
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
