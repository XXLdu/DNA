package com.ruoyi.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.code.domain.FileData;
import com.ruoyi.code.domain.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.code.mapper.SysFileInfoMapper;
import com.ruoyi.code.domain.SysFileInfo;
import com.ruoyi.code.service.ISysFileInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-08
 */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService
{
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息ID
     * @return 文件信息
     */
    @Override
    public SysFileInfo selectSysFileInfoById(String fileId)
    {
        return sysFileInfoMapper.selectSysFileInfoById(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param sysFileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 修改文件信息
     * 
     * @param sysFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 删除文件信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByIds(String ids)
    {
        return sysFileInfoMapper.deleteSysFileInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息ID
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoById(String fileId)
    {
        return sysFileInfoMapper.deleteSysFileInfoById(fileId);
    }


    //获取页面回显文件信息
    public FileData getFileData(String fid){
        //获取附件列表
        SysFileInfo sysFileInfo = new SysFileInfo();
        sysFileInfo.setFatherId(fid);
        List<SysFileInfo> fileList = selectSysFileInfoList(sysFileInfo);
        List<String> initialPreview = new ArrayList<String>();
        List<FileObject> initialPreviewConfig = new ArrayList<FileObject>();
        FileObject fileObejct = new FileObject();
        for (int i=0;i<fileList.size();i++){
            sysFileInfo = fileList.get(i);
            initialPreview.add(sysFileInfo.getFilePath());

            fileObejct.setKid(sysFileInfo.getFileId());
            fileObejct.setCaption(sysFileInfo.getFileName());
            fileObejct.setType(sysFileInfo.getFilePath().substring(sysFileInfo.getFilePath().lastIndexOf(".")+1));
            fileObejct.setUrl("/code/info/remove?ids="+sysFileInfo.getFileId());
            initialPreviewConfig.add(fileObejct);
        }
        FileData data = new FileData();
        data.setInitialPreview(initialPreview);
        data.setInitialPreviewConfig(initialPreviewConfig);
        return data;
    }
}
