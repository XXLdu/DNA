package com.ruoyi.common.utils.file;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GreatJdws {
    private static String outPath = "D:\\JXGA\\out\\";
    private static String inpPath = "D:\\JXGA\\DNAmuban.doc";

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("{bh}", "编号");//编号
        map.put("{wtdw}", "委托单位");//委托单位
        map.put("{sjr}", "送检人");//送检人
        map.put("{slrq}", "送检日期");//送检日期
        map.put("{ajzy}", "案件摘要");//案件摘要
        map.put("{jcyb}", "检材样本");//检材样本
        map.put("{jdyq}", "鉴定要求"); //鉴定要求
        map.put("{jyksrq}", "检验开始日期"); //检验开始日期
        map.put("{jydd}", "检验地点"); //检验地点
        map.put("{dna}", "dna列表"); //dna列表
        map.put("{jyxlh}", "基因序列号");   //基因序列号
        map.put("{jdyj}", "鉴定意见"); //鉴定意见
        map.put("{dyjdr}", "第一鉴定人"); //第一鉴定人
        map.put("{dejdr}", "第二鉴定人");  //第二鉴定人
        map.put("{sqqzr}", "授权签字人");  //授权签字人
        map.put("{sj}", "时间");  //时间
        readwriteWord(map);

    }

    /**
     * 实现对word读取和修改操作
     *
     * @param
     * @param map
     *            待填充的数据，从数据库读取
     */
    public static String readwriteWord(Map<String, String> map)
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(new File(inpPath));
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        HWPFDocument hdt = null;
        try
        {
            hdt = new HWPFDocument(in);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        //读取word文本内容
        Range range = hdt.getRange();
        // 替换文本内容  ````````````
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            range.replaceText(entry.getKey(), entry.getValue());
        }
        String fileName = "" + System.currentTimeMillis();
        fileName += ".doc";
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(outPath + fileName, true);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            hdt.write(out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 输出字节流
        try
        {
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            System.out.println("生成鉴定文书成功");
            return WordToHtml.Word2003ToHtml(outPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成鉴定文书失败");
            return "生成鉴定文书失败";
        }
    }
}