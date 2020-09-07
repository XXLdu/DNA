package com.ruoyi.code.service;

import java.util.List;

import com.ruoyi.code.domain.FileData;
import com.ruoyi.code.domain.SysFileInfo;

/**
 * 文件信息Service接口
 * 
 * @author ruoyi
 * @date 2020-07-08
 */
public interface ISysFileInfoService 
{
    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息ID
     * @return 文件信息
     */
    public SysFileInfo selectSysFileInfoById(String fileId);

    /**
     * 查询文件信息列表
     * 
     * @param sysFileInfo 文件信息
     * @return 文件信息集合
     */
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo);

    /**
     * 新增文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    public int insertSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 修改文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    public int updateSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 批量删除文件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileInfoByIds(String ids);

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息ID
     * @return 结果
     */
    public int deleteSysFileInfoById(String fileId);

    //获取页面回显文件信息
    public FileData getFileData(String fid);

}
