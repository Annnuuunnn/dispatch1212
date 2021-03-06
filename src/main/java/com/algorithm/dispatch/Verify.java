package com.algorithm.dispatch;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证
 */
public class Verify {
    private List<Integer[][]> fit = new ArrayList<>();
    private Integer[][] array;

    public List<Integer[][]> getFit() {
        return fit;
    }

    public void setFit(List<Integer[][]> fit) {
        this.fit = fit;
    }

    public Integer[][] getArray() {
        return array;
    }

    public void setArray(Integer[][] array) {
        this.array = array;
    }

    private static boolean judge(Integer[][] arrayList) {
        if (arrayList.length == 0) {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < arrayList.length; i++) {
            int count1 = 0;
            for (int j = 0; j < arrayList[i].length; j++) {
                int count2 = 0;
                for (Integer[] array : arrayList) {
                    if (array[j] == 1) {
                        count2++;
                    }
                }
                if (count2 == 1 && arrayList[i][j] == 1) {
                    count1++;
                }
            }
            if (count1 < 1) {
                return false;
            }
        }
        return true;
    }

    private boolean verify(Integer[][] arrayList, int n) {
        if (arrayList.length == 0) {
            return false;
        }
        if (n == 0) {
            DispatchUtils.deepCopy(arrayList, array);
//            System.out.println("当前验证调度方案：");
//            for (Integer[] a : arrayList) {
//                System.out.println(Arrays.toString(a));
//            }
//            System.out.println("----------------------");
        }
        boolean flag = true;
        if (n != arrayList.length - 1) {
            for (int i = 0; i < arrayList[0].length + 1; i++) {
                if (i > 0) {
                    arrayList = array;
                    arrayList[n] = DispatchUtils.moveArrayElement(arrayList[n], i);
                }
                if (i == arrayList[0].length) {
//                    System.out.println("完成第" + (n + 1) + "层");
                    break;
                }
                flag = verify(arrayList, n + 1);
                if (!flag) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < arrayList[0].length + 1; i++) {
                if (i > 0) {
                    arrayList = array;
                    arrayList[n] = DispatchUtils.moveArrayElement(arrayList[n], i);
                }
                if (i == arrayList[0].length) {
//                    System.out.println("完成最内层");
                    break;
                }
                flag = judge(arrayList);

                if (!flag) {
//                    for (Integer[] a : arrayList) {
//                        System.out.println(Arrays.toString(a));
//                    }
//                    System.out.println("该情况下验证不通过\n");
                    return false;
                }
            }
        }
//        if(n == 0) {
//            System.out.println("调度方案：");
//            for (Integer[] a : arrayList) {
//                System.out.println(Arrays.toString(a));
//            }
//            System.out.println("验证通过！");
//        }
        return true;
    }

    public void nextArray(Integer[][] arrayList, int i) {
        if (arrayList.length == 0) {
            return;
        }
        boolean flag = true;
        if (i != arrayList.length - 1) {
            nextArray(arrayList, i + 1);
        } else {
            flag = verify(arrayList, 0);
            if (flag) {
                Integer[][] copy = new Integer[arrayList.length][arrayList[0].length];
                DispatchUtils.deepCopy(arrayList, copy);
                fit.add(copy);
            }
        }
    }
}
