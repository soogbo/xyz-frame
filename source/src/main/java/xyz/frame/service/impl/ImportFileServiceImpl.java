package xyz.frame.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mapper.QuizzesMapper;
import xyz.frame.pojo.po.Quizzes;
import xyz.frame.service.ImportFileService;

@Service("importFileService")
public class ImportFileServiceImpl implements ImportFileService {
    private static final Logger logger = LoggerFactory.getLogger(ImportFileServiceImpl.class);

    @Autowired
    private QuizzesMapper quizzesMapper;

    public void ImportFileToDb(String fileName) {
        List<Quizzes> quizzesList = new ArrayList<>();
        File txtFile = new File("C:\\Users\\shisp\\Desktop\\quiz\\"+fileName);
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile)));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line))
                    break;
                Quizzes quizzes = buildQuizzesRecord(line);
                if (quizzes != null) {
                    quizzesList.add(quizzes);
                }
            }
            
            int i = 1;
            for (Quizzes quizzes : quizzesList) {
                try {
                    quizzesMapper.insert(quizzes);
                    i++;
                    if (i%10==0) {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    logger.info(FrameJsonUtils.toJson(quizzes));
                }
            }
            /*if (quizzesList != null && !quizzesList.isEmpty()) {
                for (Iterator<Quizzes> iterator = quizzesList.iterator(); iterator.hasNext();) {
                    List<Quizzes> iteratorList = new ArrayList<>();
                    for (int j = 1; j < 200 && iterator.hasNext(); j++) {
                        iteratorList.add(iterator.next());
                    }
                    logger.info("获取m数据....更新{}条", iteratorList.size());
                    if (iteratorList != null && !iteratorList.isEmpty()) {
                        quizzesMapper.insertList(iteratorList);
                    }
                }
            }*/

        } catch (Exception e) {
            logger.info("获取数据异常 ：", e.getMessage(), e.toString());
        }
    }

    private Quizzes buildQuizzesRecord(String line) {
        try {
            if (StringUtils.isBlank(line)) {
                return null;
            }
            JSONObject jsonObject = JSON.parseObject(line);
            Quizzes record = new Quizzes();
            
            record.setAnswer(jsonObject.getIntValue("answer"));
            record.setContributor(jsonObject.getString("contributor"));
            record.setCreateAt(jsonObject.getJSONObject("meta").getDate("createAt"));
            record.setCurTime(jsonObject.getString("curTime"));
            record.setEndTime(jsonObject.getString("endTime"));
            record.setOptions(jsonObject.getString("options"));
            record.setQuiz(jsonObject.getString("quiz"));
            record.setSchool(jsonObject.getString("school"));
            record.setType(jsonObject.getString("type"));
            record.setUpdatedAt(jsonObject.getJSONObject("meta").getDate("updateAt"));
            
            return record;
        } catch (Exception ex) {
            logger.error("Quizzes数据转换异常:{},{}", line, ex.getMessage(), ex);
            return null;
        }
    }
}
