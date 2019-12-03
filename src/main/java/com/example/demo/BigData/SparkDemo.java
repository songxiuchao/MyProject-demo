/*
package com.example.demo.BigData;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @program: demo
 * @description: Apache Spark
 * @author: xiuchao Song
 * @create: 2019-12-03 14:02
 **//*


public class SparkDemo {
    private static String appName = "spark.demo";
    private static String master = "local[*]";

    public static void main(String[] args) {
        JavaSparkContext sc = null;
        try {
            //初始化JavaSparkContext
            SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
            sc = new JavaSparkContext(conf);

            // 生成数据源
            List<Student> data = getList();

            //生成rdd
            JavaRDD<Student> rdd = sc.parallelize(data);

            //过滤符合条件的数据
            rdd = rdd.filter(new Function<Student, Boolean>() {
                public Boolean call(Student s) throws Exception {
                    return s.isGaoKao() && s.getSex().equals("男");
                }
            });

            // map && reduce
            Student result = rdd.map(new Function<Student, Student>() {
                public Student call(Student s) throws Exception {
                    s.setCount(1);
                    return s;
                }
            }).reduce(new Function2<Student, Student, Student>() {
                public Student call(Student s1, Student s2) throws Exception {
                    s1.setCount(s1.getCount() + s2.getCount());
                    return s1;
                }
            });

            System.out.println("执行结果：" + result.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static List<Student> getList(){
        List<Student> data = new ArrayList<Student>();
        data.add(new Student(true,"男", "A"));
        data.add(new Student(false,"女", "B"));
        data.add(new Student(false,"男", "C"));
        data.add(new Student(true,"女", "D"));
        data.add(new Student(true,"男", "E"));
        data.add(new Student(false,"女", "F"));
        data.add(new Student(true,"男", "G"));
        return data;
    }

    static class Student implements Serializable{
        private String name;
        private boolean gaoKao;
        private String sex;
        private int count;

        public Student(boolean gaoKao, String sex, String name) {
            this.gaoKao = gaoKao;
            this.sex = sex;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isGaoKao() {
            return gaoKao;
        }

        public void setGaoKao(boolean gaoKao) {
            this.gaoKao = gaoKao;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}*/
