package com.imooc.utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: zhouyanyang
 * @Date: 2019-08-21 23:52
 * @Description:
 */

public class SimpleBloomFilter {

    /* BitSet初始分配2^24个bit */
    private static final int DEFAULT_SIZE = 1 << 25;
    /* 不同哈希函数的种子，一般应取质数 */
    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 43, 47, 53, 61, 67, 71, 73, 79, 83, 89, 97};
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    /* 哈希函数对象 */
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public SimpleBloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 将字符串标记到bits中
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    // 将字符串标记到bits中
    public void addAll(List<String> list) {
        for (String s : list) {
            if (s.indexOf("_") > 0) {
                String[] data = s.split("_");
                String tmp = data[0].trim();
                if (!contains(tmp)) {
                    for (SimpleHash f : func) {
                        bits.set(f.hash(tmp), true);
                    }
                }
            } else {
                for (SimpleHash f : func) {
                    bits.set(f.hash(s), true);
                }
            }
        }
    }

    // 判断字符串是否已经被bits标记
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /* 哈希函数类 */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        // hash函数，采用简单的加权和hash
        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }


    //双重校验锁获取一个Random单例
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
		/*if(random==null){
			synchronized (RandomUtils.class) {
				if(random==null){
					random =new Random();
				}
			}
		}

		return random;*/
    }

    /**
     * 获得一个[0,max)之间的随机整数。
     *
     * @param max
     * @return
     */
    public static int getRandomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * 从map中随机取得一个key
     *
     * @param map
     * @return
     */
    public static <K, V> K getRandomKeyFromMap(Map<K, V> map, int rn) {
        int i = 0;
        for (K key : map.keySet()) {
            if (i == rn) {
                return key;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个value
     *
     * @param map
     * @return
     */
    public static <K, V> V getRandomValueFromMap(Map<K, V> map) {
        int rn = getRandomInt(map.size());
        int i = 0;
        for (V value : map.values()) {
            if (i == rn) {
                return value;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个value
     * //     * @param map
     *
     * @return
     */
    public static List<String> getRandomValueFromMapFeed(Map<String, List<String>> map, int avg) {
//        if (avg > map.size()) {
//            int[] rns = randomArray(0, map.size() - 1, map.size());
//            if (rns == null) {
//                return Collections.emptyList();
//            }
//            List<String> sub = new ArrayList<>();
//            for (int j = 0; j < avg / rns.length; j++) {
//                for (int i = 0; i < rns.length; i++) {
//                    String key = getRandomKeyFromMap(map, rns[i]);
//                    avg = avg > map.get(key).size() ? map.get(key).size() : avg;
//                    int[] avgs = randomArrayForTop(0, map.get(key).size() - 1, avg);
//                    for (int s = 0; s < avgs.length; s++) {
//                        if (key != null) {
//                            sub.add(map.get(key).get(avgs[s]));
//                        }
//                    }
//                }
//            }
//            return sub;
//        }
        int[] rns = randomArray(0, map.size() - 1, map.size());
        if (rns == null) {
            return Collections.emptyList();
        }
        List<String> sub = new ArrayList<>();
        for (int j = 0; j < avg / rns.length; j++) {
            for (int i = 0; i < rns.length; i++) {
                String key = getRandomKeyFromMap(map, rns[i]);
                if (key != null) {
                    sub.add(map.get(key).get(getRandomInt(map.get(key).size() / 8 + 1)));
                }
            }
        }
        return sub;
    }


    /**
     * @param a 结合前面的int型数组
     * @param b 结合后面的int型数组
     * @return a+b的结合的int型数组
     */
    public static int[] combineIntData(int[] a, int[] b) {

        ArrayList<Integer> alist = new ArrayList<Integer>(a.length + b.length);

        for (int j = 0; j < a.length; j++) {
            alist.add(a[j]);
        }

        for (int k = 0; k < b.length; k++) {
            alist.add(b[k]);
        }

        int[] c = new int[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            c[i] = alist.get(i);
        }
        return c;
    }


    public static int[] randomArrayForTop(int min, int max, int n) {
        if (min == 0 && max == 0) {
            return new int[]{0};
        }

        int len = max - min + 1;
        if (n > len) {
            n = len;
        }

        if (max < min) {
            return new int[]{};
        }

        int num = n / 5 * 4;

        if (num == 0) {
            if (n != 1 && n > 0) {
                num = n - 1;
            } else {
                num = n;
            }

        }

        int mid = max / 10 > n - 1 ? max / 10 : n - 1;

        int[] top = randomArray(min, mid, num);
        int[] normal = randomArray(mid + 1, max, n - num);

        if (top != null && normal != null) {
            return combineIntData(top, normal);
        }
        if (top != null) {
            return top;
        }
        if (normal != null) {
            return normal;
        }
        return null;
    }

    public static int[] IntegerArrayToInt(Integer[] integers){
        if (integers!=null){
            int[] ints = new int[integers.length];
            for (int i = 0;i<integers.length;i++){
                ints[i] = integers[i];
            }
            return  ints;
        }else {
            return  null;
        }

    }


    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;

        if (n >= len) {
            n=len;
        }
        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

}

