package com.ruoyi.code.utils;

import com.ruoyi.common.utils.file.WordToHtml;
import com.ruoyi.common.utils.poi.DocUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GreateJdws {

    private static String TemplateFile = "鉴定文书.doc";
    private static String courseFile = "";//当前文件路径

    //获取当前文件路径
    static void getCourseFile(){
        File directory = new File("");//参数为空
        try {
            courseFile = directory.getCanonicalPath()+"\\ruoyi-admin\\src\\main\\resources\\static\\file\\";

        } catch (IOException e) {
            e.printStackTrace();
        }
        //courseFile = "D:\\JXGA\\";
    }

    /**
     * 实现对word读取和修改操作
     * @param map
     */
    public static String readwriteWord(Map<String, String> map)
    {
        if("".equals(courseFile)){
            getCourseFile();
        }
        String inpPath = courseFile + TemplateFile;
        String outPath = courseFile + System.currentTimeMillis()+".doc";
        try {
            DocUtil.searchAndReplaceDoc(inpPath,outPath,map);
            System.out.println("生成鉴定文书成功");
            return WordToHtml.Word2003ToHtml(outPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成鉴定文书失败");
            return "生成鉴定文书失败";
        }
    }


    public static void main(String[] args)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("bh", "编号");//编号
        map.put("wtdw", "委托单位");//委托单位
        map.put("sjr", "送检人");//送检人
        map.put("slrq", "送检日期");//送检日期
        map.put("ajzy", "案件摘要");//案件摘要
        map.put("jcyb", "检材样本");//检材样本
        map.put("jdyq", "鉴定要求"); //鉴定要求
        map.put("jyksrq", "检验开始日期"); //检验开始日期
        map.put("jydd", "检验地点"); //检验地点
        map.put("dna", "dna列表"); //dna列表
        map.put("jyxlh", "基因序列号");   //基因序列号
        map.put("jdyj", "鉴定意见"); //鉴定意见
        map.put("dyjdr", "第一鉴定人"); //第一鉴定人
        map.put("dejdr", "第二鉴定人");  //第二鉴定人
        map.put("sqqzr", "授权签字人");  //授权签字人
        //map.put("time", "时间");  //时间
        System.out.println(readwriteWord(map));
    }
}