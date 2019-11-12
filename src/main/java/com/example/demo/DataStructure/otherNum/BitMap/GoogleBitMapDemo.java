package com.example.demo.DataStructure.otherNum.BitMap;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.io.IOException;

/**
 * @author idea
 * @data 2019/4/1
 */
public class GoogleBitMapDemo {

    public static void main(String[] args) throws IOException {
        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1 << 30);
        EWAHCompressedBitmap ewahBitmap2 = EWAHCompressedBitmap.bitmapOf(1, 3, 64, 1 << 30);
        System.out.println("bitmap 1: " + ewahBitmap1);
        System.out.println("bitmap 2: " + ewahBitmap2);

        //实现并集
        EWAHCompressedBitmap orbitmap = ewahBitmap1.or(ewahBitmap2);
        System.out.println(orbitmap);

        //实现交集
        EWAHCompressedBitmap andbitmap = ewahBitmap1.and(ewahBitmap2);
        System.out.println(andbitmap);

        //xor操作
        EWAHCompressedBitmap xorbitmap = ewahBitmap1.xor(ewahBitmap2);
        System.out.println(xorbitmap);

        //多个bitmap的共同部分
        EWAHCompressedBitmap ewahBitmap3 = EWAHCompressedBitmap.bitmapOf(5, 55,
                1 << 30);
        EWAHCompressedBitmap ewahBitmap4 = EWAHCompressedBitmap.bitmapOf(4, 66,
                1 << 30);
        System.out.println("bitmap 3: " + ewahBitmap3);
        System.out.println("bitmap 4: " + ewahBitmap4);
        andbitmap = EWAHCompressedBitmap.and(ewahBitmap1, ewahBitmap2, ewahBitmap3,
                ewahBitmap4);
        System.out.println("b1 AND b2 AND b3 AND b4: " + andbitmap);
        boolean result = ewahBitmap1.get(0);

        System.out.println(result);

        EWAHCompressedBitmap ew=EWAHCompressedBitmap.bitmapOf(1,23,4,5,67);
        ew.set(12);
    }
}
