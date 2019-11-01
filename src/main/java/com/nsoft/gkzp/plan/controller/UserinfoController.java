package com.nsoft.gkzp.plan.controller;

import com.nsoft.gkzp.common.FileLoad;
import com.nsoft.gkzp.common.entity.FileVo;
import com.nsoft.gkzp.common.entity.HrRecruitFile;
import com.nsoft.gkzp.plan.entity.*;
import com.nsoft.gkzp.plan.service.*;
import com.nsoft.gkzp.syscore.config.MyDefinedUtil;
import com.nsoft.gkzp.syscore.web.AbstractController;
import com.nsoft.gkzp.syscore.web.UserContext;
import com.nsoft.gkzp.util.DataFormat;
import com.nsoft.gkzp.util.Page;
import com.nsoft.gkzp.util.ResultMsg;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserinfoController extends AbstractController {
    //基础信息
    @Autowired
    HrRecruitEntryinfoBaseService hrRecruitEntryinfoBaseService;

    //体检
    @Autowired
    HrRecruitHealthchkService hrRecruitHealthchkService;

    //面试
    @Autowired
    HrRecuritInterviewService hrRecuritInterviewService;

    //笔试
    @Autowired
    HrRecuritWriteService hrRecuritWriteService;

    //资格审核
    @Autowired
    HrRecruitReviewRecordService hrRecruitReviewRecordService;

    //消息
    @Autowired
    HrRecruitNoticeService hrRecruitNoticeService;

    //岗位
    @Autowired
    HrRecuritPlanNeedsService hrRecuritPlanNeedsService;

    //数据转换
    @Autowired
    DataFormat dataFormat;

    //文件管理
    @Autowired
    FileLoad fileLoad;

    //配置参数
    @Autowired
    private MyDefinedUtil myDefinedUtil;

    /**
     * 根据登录用户查询信息
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getInfoByUser")
    public HrRecruitEntryinfo getInfoByUser(HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecruitEntryinfoBaseService.getInfoByUser(userContext);
    }


    /**
     * 基础信息新增
     * @param data
     * @return
     */
    @RequestMapping(value="intercept/plan/userInfo/add",method= RequestMethod.POST)
    public ResultMsg add(String data, HttpServletRequest request){
        ResultMsg resultMsg = new ResultMsg();
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            logger.info(userContext);
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);

            hrRecruitEntryinfoBaseService.add(jsonObject,userContext);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"");
            return resultMsg;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //错误信息
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"");
        return resultMsg;
    }

    /**
     * 修改
     * @param data
     * @param request
     * @return
     */
    @RequestMapping(value="intercept/plan/userInfo/edit",method= RequestMethod.POST)
    public ResultMsg edit(String data, HttpServletRequest request){
        ResultMsg resultMsg = new ResultMsg();
        try {

            UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
            //转义转JSON
            data = URLDecoder.decode(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(data);


            //检验身份证
            /*boolean verifyIdCard = hrRecruitEntryinfoBaseService.verifyIdCard(jsonObject.getJSONObject("baseInfo").getString(""));
            if(verifyIdCard){
                resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"身份证已存在");
                return resultMsg;
            }*/

            hrRecruitEntryinfoBaseService.edit(jsonObject,userContext);

            //成功信息
            resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"");
            return resultMsg;
        } catch (Exception e) {
            e.printStackTrace();

        }
        //错误信息
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"");
        return resultMsg;
    }

    /**
     * 考核管理
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/review")
    public HashMap<String, Object> review(HttpServletRequest request) {

        HashMap<String, Object> result = new HashMap<>();
        //设置默认值为null
        result.put("HrRecuritWrite",null);
        result.put("HrRecuritWriteMsg",null);
        result.put("HrRecuritInterview",null);
        result.put("HrRecuritInterviewMsg",null);
        result.put("HrRecruitHealthchk",null);
        result.put("HrRecruitHealthchkMsg",null);
        result.put("HrRecruitReviewRecord",null);
        result.put("HrRecruitReviewRecordMsg",null);

        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);

        if(baseId == 0)return null;

        //岗位信息
        HrRecuritPlanNeedsVo hrRecuritPlanNeedsVoByUser = hrRecuritPlanNeedsService.getHrRecuritPlanNeedsVoByUser(userContext);

        if(hrRecuritPlanNeedsVoByUser == null) return null;
        result.put("HrRecuritPlanNeeds",hrRecuritPlanNeedsVoByUser.getHrRecuritPlanNeedsDo());

        //获取通知消息
        HrRecruitNotice hrRecruitNoticeSelect = new HrRecruitNotice();
        hrRecruitNoticeSelect.setBaseid(baseId);
        hrRecruitNoticeSelect.setStatus(1);
        List<HrRecruitNotice> hrRecruitNotices = hrRecruitNoticeService.list(hrRecruitNoticeSelect, null, null);

        //遍历消息读取类型
        for (HrRecruitNotice hrRecruitNotice:hrRecruitNotices) {
            int type = hrRecruitNotice.getType();
            if(type == 1){ //笔试
                HrRecuritWrite hrRecuritWrite = hrRecuritWriteService.getHrRecuritWriteByBaseId(baseId);
                result.put("HrRecuritWrite",hrRecuritWrite);
                result.put("HrRecuritWriteMsg",hrRecruitNotice);
            } else if(type == 2){ //面试
                HrRecuritInterview hrRecuritInterview = hrRecuritInterviewService.getHrRecuritInterviewByBaseId(baseId);
                result.put("HrRecuritInterview",hrRecuritInterview);
                result.put("HrRecuritInterviewMsg",hrRecruitNotice);
            } else if(type == 3){ //体检
                HrRecruitHealthchk hrRecruitHealthchk = hrRecruitHealthchkService.getHrRecruitHealthchkByBaseId(baseId);
                result.put("HrRecruitHealthchk",hrRecruitHealthchk);
                result.put("HrRecruitHealthchkMsg",hrRecruitNotice);
            } else if(type == 4){ //审核结果
                HrRecruitReviewRecord hrRecruitReviewRecord = hrRecruitReviewRecordService.getHrRecruitReviewRecordByBaseId(baseId);
                result.put("HrRecruitReviewRecord",hrRecruitReviewRecord);

                String affix = hrRecruitNotice.getAffix();
                //文件不为空
                if(affix != null && affix != ""){
                    //查询文件
                    Integer[] affixArr = dataFormat.stringArrToIntArr(affix.split(","));
                    List<FileVo> fileList = fileLoad.getFileListByIds(affixArr);
                    hrRecruitNotice.setAffixList(fileList);
                }
                result.put("HrRecruitReviewRecordMsg",hrRecruitNotice);
            }
        }

        return result;
    }

    /**
     * 通过用户获取体检数据
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecruitHealthchkByUser")
    public HrRecruitHealthchk getHrRecruitHealthchkByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecruitHealthchkService.getHrRecruitHealthchkByUser(userContext);
    }

    /**
     * 通过用户获取面试数据
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecuritInterviewByUser")
    public HrRecuritInterview getHrRecuritInterviewByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritInterviewService.getHrRecuritInterviewByUser(userContext);
    }

    /**
     * 通过用户获取笔试数据
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecuritWriteByUser")
    public HrRecuritWrite getHrRecuritWriteByUser(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        return hrRecuritWriteService.getHrRecuritWriteByUser(userContext);
    }

    /**
     * 通过用户获取资格审核
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/getHrRecruitReviewRecordVoByUser")
    public HrRecruitReviewRecordVo getHrRecruitReviewRecordVoByUser(HttpServletRequest request) {
        HrRecruitReviewRecordVo result = new HrRecruitReviewRecordVo();
        
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        HrRecruitReviewRecordVo hrRecruitReviewRecordVo = hrRecruitReviewRecordService.getHrRecruitReviewRecordVoByUser(userContext);

        result.setHrRecruitReviewRecord(hrRecruitReviewRecordVo.getHrRecruitReviewRecord());
        //建一个新的基础信息
        HrRecruitEntryinfoBase hrRecruitEntryinfoBase = new HrRecruitEntryinfoBase();
        //只获取报名时间
        hrRecruitEntryinfoBase.setSignuptime(hrRecruitReviewRecordVo.getHrRecruitEntryinfoBase().getSignuptime());
        result.setHrRecruitEntryinfoBase(hrRecruitEntryinfoBase);
        return result;
    }

    /**
     * 用户消息
     * @param request
     * @return
     */
    @RequestMapping("intercept/plan/userInfo/userMsg")
    public List<HrRecruitNotice> userMsg(HttpServletRequest request, int hasRead, Page page) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        //获取基础信息id
        int baseId = hrRecruitEntryinfoBaseService.getBaseIdByUser(userContext);

        HrRecruitNotice hrRecruitNotice = new HrRecruitNotice();
        hrRecruitNotice.setBaseid(baseId);
        hrRecruitNotice.setStatus(1);
        hrRecruitNotice.setHasread(hasRead);
        List<HrRecruitNotice> list = hrRecruitNoticeService.list(hrRecruitNotice, null, page);
        return list;
    }

    /**
     * 设为阅读
     * @param id
     */
    @RequestMapping("intercept/plan/userInfo/read")
    public void read(int id){
        hrRecruitNoticeService.read(id);
    }

    /**
     * 用户所有设为已读
     * @param request
     */
    @RequestMapping("intercept/plan/userInfo/userReadAll")
    public void userReadAll(HttpServletRequest request) {
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        hrRecruitNoticeService.userReadAll(userContext);
    }


    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("plan/plan/upload/img")
    public ResultMsg uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        UserContext userContext = (UserContext) WebUtils.getSessionAttribute(request,"userContext");
        //指定本地文件夹存储图片
        String filePath = myDefinedUtil.SYSTEM_FILE_FOLDER_IMG;
        ResultMsg resultMsg = fileLoad.uploadFile(userContext,file, filePath);
        return resultMsg;
    }

    /**
     * 下载头像
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping("plan/plan/download/img/{id}")
    public void downloadImg(HttpServletResponse response, @PathVariable int id) throws Exception {

        hrRecruitEntryinfoBaseService.downloadImg(response,id);

    }

    @RequestMapping("plan/userInfo/syncFile")
    public ResultMsg syncFile(HttpServletResponse response) {
        ResultMsg resultMsg = new ResultMsg();

        //查询未同步的文件
        HrRecruitFile hrRecruitFile = new HrRecruitFile();
        hrRecruitFile.setSyncfile(1);
        List<HrRecruitFile> hrRecruitFiles = fileLoad.fileList(hrRecruitFile, null, null);

        for (HrRecruitFile hrRecruitFileEach:hrRecruitFiles) {
            String filecname = hrRecruitFileEach.getFilecname();
            String fileurl = hrRecruitFileEach.getFileurl();
            try {
                fileLoad.downloadFile(response,filecname,fileurl);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("同步文件时报错。id="+hrRecruitFileEach.getId());
            }
        }

        resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"同步成功");
        return resultMsg;
    }

    /**
     * 用户信息同步文件
     */
    /*@RequestMapping("plan/userInfo/syncFile")
    public ResultMsg syncFile() throws IOException {
        try {
            //查询未同步的文件
            HrRecruitFile hrRecruitFile = new HrRecruitFile();
            hrRecruitFile.setSyncfile(1);
            List<HrRecruitFile> hrRecruitFiles = fileLoad.fileList(hrRecruitFile, null, null);
            //保存文件进容器
            Map<String, File> files = new HashMap<String, File>();
            //本次的id集合
            List<Integer> ids = new ArrayList<>();
            for (HrRecruitFile hrRecruitFileEach:hrRecruitFiles) {
                //保存文件
                File file = new File(hrRecruitFileEach.getFileurl());
                files.put(file.getName(), file);

                //保存id
                ids.add(hrRecruitFileEach.getId());
            }
            //同步
            String result = fileLoad.upLoadFilePost(myDefinedUtil.USERINFO_SYNCFILE_URL, files);
            //判断是否同步成功  成功进行修改本次同步文件的值
            if(result != null && result.equals("ok")){
                Example example = new Example(HrRecruitEntryinfoWork.class);
                example.createCriteria().andIn("id",ids);

                HrRecruitFile hrRecruitFileEdit = new HrRecruitFile();
                hrRecruitFileEdit.setSyncfile(2);
                fileLoad.fileEditExample(hrRecruitFileEdit,example);
                resultMsg.setResultMsg(ResultMsg.MsgType.INFO,"同步成功");
                return resultMsg;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        resultMsg.setResultMsg(ResultMsg.MsgType.ERROR,"同步失败");
        return resultMsg;
    }*/
}
