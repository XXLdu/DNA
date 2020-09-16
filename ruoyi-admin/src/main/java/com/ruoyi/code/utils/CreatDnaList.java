package com.ruoyi.code.utils;

import com.ruoyi.code.domain.DnaData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreatDnaList {
    private static String from = "dat";
    private static String to = "txt";

    //根据dat文件，解析获取基因序列编码信息list
    public static List getDnaDataList(String path,String sampleId) {
        File file = reName(path);
        List<DnaData> dnaDataList = txt2List(file,sampleId);
        return dnaDataList;
    }

    //修改dat文件后缀名为txt
    private static File reName(String path) {
        File subFile = new File(path);
        // 如果文件是文件夹则递归调用批量更改文件后缀名的函数
        if (subFile.isDirectory()) {
            reName(subFile.getPath());
        } else {
            String name = subFile.getName();
            if (name.endsWith(from)) {
                File toFile = new File(subFile.getParent() + "/" + name.substring(0, name.indexOf(from)) + to);
                subFile.renameTo(toFile);
                return toFile;
            }else{
                return null;
            }
        }
        return null;
    }

    /**
     * 读取txt文件的内容 * @param file 想要读取的文件对象
     * @return 返回基因序列编码list
     */
    private static List txt2List(File file,String sampleId){
        List<DnaData> jyxlbmList = new ArrayList<>();//定义基因序列编码容器
        String jyxl = "";//定义基因序列
        String jybm = "";//定义基因编码

        //定义指针 readLine()方法，每执行一次，下移一行，并读取内容
        int jyxlnum;//定义基因序列数量
        int jybmnum;//定义基因编码数量

        //开始读取文件内容
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件

            for(int num=0;num<18;num++){br.readLine();}//指针下移18行
            jyxlnum = Integer.parseInt(br.readLine());//指针下移1行,获取基因序列数量 jyxlnum

            for(int i=0; i<jyxlnum; i++){//循环jyxlnum次

                jyxl = br.readLine();//指针下移1行,获取基因序列

                for(int num=0;num<4;num++){br.readLine();}//指针下移4行
                jybmnum = Integer.parseInt(br.readLine());//指针下移1行,获取基因编码数量

                for(int j=0; j<jybmnum; j++){

                    jybm = br.readLine();//指针下移1行,获取基因编码

                    DnaData dnaData = new DnaData();//基因序列编码
                    dnaData.setId(UUID.randomUUID().toString().replaceAll("-",""));
                    dnaData.setSample_id(sampleId);
                    dnaData.setCode(jyxl);
                    dnaData.setValue(jybm);
                    dnaData.setSort(i+1+"");
                    jyxlbmList.add(dnaData);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return jyxlbmList;
    }

}
