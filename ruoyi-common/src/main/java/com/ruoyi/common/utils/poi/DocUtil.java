package com.ruoyi.common.utils.poi;

import com.ruoyi.common.utils.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class DocUtil {

    public static void searchAndReplace(String srcPath, String destPath, Map<String, String> map) {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().trim();
                    List<XWPFRun> run = paragraph.getRuns();
                    int runSize = run.size();
                    for (int i = 0; i < runSize; i++) {
                        String text = run.get(i).getText(0);
                        System.out.println("++++++text++++++:" + text);
                        for (Entry<String, String> e : map.entrySet()) {
                            if (text != null && text.contains(e.getKey())) {
                                text = text.replace(e.getKey(), e.getValue());
                                System.out.println("++++++text222222222++++++:" + text);
                                run.get(i).setText(text, 0);
                            }
                        }
                    }
                }
            }

            /**
             * 替换表格中的指定文字
             */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = (XWPFTable) itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                for (Entry<String, String> e : map.entrySet()) {
                                    if (text != null && text.contains(e.getKey())) {
                                        text = text.replace(e.getKey(), e.getValue());
                                        r.setText(text, 0);
                                    }
                                }
                            }
                        }

                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void listDicTory(File src, File dest) throws IllegalAccessException, IOException {
        /*此处用来判断文件是否存在*/
        if (src.exists() && dest.exists()) {
            /*此处用来判断文件是否是文件类型*/
            if (src.isFile() && dest.isFile()) {
                /*此处用来声明需要copy的文件*/
                FileInputStream input = new FileInputStream(src);
                /*此处用来声明需要copy到的文件*/
                FileOutputStream out = new FileOutputStream(dest, false);
                /*此处用来定义一个缓冲区存放读取的文件*/
                byte buf[] = new byte[8 * 1024];
                int b;
			/*此处用来判断文件是否读完，注意点
            (b=input.read(buf,0,buf.length))!=-1：表示读取到buf缓冲区，从文件的0号位开始读取，最多只能暂存buf.length个字节
            */
                while ((b = input.read(buf, 0, buf.length)) != -1) {
                    /*此处用来将缓冲区的内容写入到out对应的文件*/
                    out.write(buf, 0, b);
                    out.flush();//最好加上
                    //关闭(close)输出流时，应先刷新(flush)换冲的输出流，话句话说就是：“迫使所有缓冲的输出数据被写出到底层输出流中”。
                }
                input.close();
                out.close();
            } else {
                throw new IllegalArgumentException("有一个或者多个文件文件是文件夹");
            }
        } else {
            throw new IllegalArgumentException("有一个或者多个文件文件不存在");
        }

    }

    public static void createFile(String filePath, String fileName) {
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现对word读取和修改操作
     *
     * @param
     * @param map 待填充的数据，从数据库读取
     */
    public static void searchAndReplaceDoc(String srcPath, String destPath, Map<String, String> map) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(srcPath));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        HWPFDocument hdt = null;
        try {
            hdt = new HWPFDocument(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //读取word文本内容
        Range range = hdt.getRange();
        // 替换文本内容  ````````````
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("++++++++++key:"+entry.getKey()+"+++++++++");
            if(StringUtils.isNull(entry.getValue())) continue;
            if("table".equals(entry.getKey())) continue;
            range.replaceText(entry.getKey(), entry.getValue());
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(destPath, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            hdt.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出字节流
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("$name$", "coco");
        map.put("$sex$", "女");
        map.put("work", "Java开发");
        String srcPath = "E:\\cocoxu\\test_mode\\sourcefile.docx";
        String destPath = "E:\\cocoxu\\test_mode\\destfile.docx";
        searchAndReplace(srcPath, destPath, map);
    }
}