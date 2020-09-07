package com.ruoyi.code.domain;

public class ProcessCode {

    /*审核流程状态标识
    0，待提交【默认值】；     10委托单位退回；
    1，委托单位待审核；
    2，待二次提交；           32鉴定单位退回；
    3，鉴定单位待审核；
    4，待检验；
    5，待生成鉴定文书；       65授权人退回； 75机构负责人退回待生成；
    6，待授权人审核文书；     76机构负责人退回授权人审核；
    7，待机构负责人审核文书；
    8，完成。*/
    public final static String wtj = "0";           public final static String wtdwth = "10";
    public final static String wtdwdsh = "1";
    public final static String dectj = "2";         public final static String jddwth = "32";
    public final static String jddwdsh = "3";
    public final static String djy = "4";
    public final static String dscjdws = "5";       public final static String sqrth = "65";
    public final static String dsqrshws = "6";      public final static String jgfzrthsqrsh = "76";
    public final static String djgfzrshws = "7";    public final static String jgfzrthdsc = "75";
    public final static String end = "8";

}
