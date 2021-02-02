package top.javahai.confucius.service.admin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import top.javahai.confucius.frame.common.helper.CollectionHelper;
import top.javahai.confucius.service.admin.entity.Subject;
import top.javahai.confucius.service.admin.entity.excel.ExcelSubjectData;
import top.javahai.confucius.service.admin.service.SubjectService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程分类导入监听器
 * @create 2021/1/14 - 10:47
 **/
@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectService subjectService;

    /**
     * 存储解析到的数据
     */
    private Map<String, List<String>> subjectMap=new HashMap<>();

    public ExcelSubjectDataListener() {
    }


    public ExcelSubjectDataListener(SubjectService subjectServices) {
        this.subjectService = subjectServices;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        log.info("parse one Excel row data,level one title:{},level two title:{}",levelOneTitle,levelTwoTitle);
        if (subjectMap.containsKey(levelOneTitle)){
            List<String> list = subjectMap.get(levelOneTitle);
            list.add(levelTwoTitle);
        }else{
            ArrayList<String> levelTwoTitleList = new ArrayList<>();
            levelTwoTitleList.add(levelTwoTitle);
            subjectMap.put(levelOneTitle,levelTwoTitleList);
        }
    }

    /**
     * 课程导入数据量不大，解析完才进行数据库插入操作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        log.info("complete parse excel data");
//        for (String key : subjectMap.keySet()) {
//            System.out.println("一级目录："+key);
//            List<String> valueList = subjectMap.get(key);
//            System.out.println("二级目录：");
//            valueList.forEach(System.out::println);
//        }
        String parentId="0";
        //数据库已有的数据
        List<Subject> subjectList = subjectService.list();
        List<String> existLevelOneList = subjectList.stream()
                .filter(subject -> parentId.equals(subject.getParentId()))
                .map(subject -> subject.getTitle())
                .collect(Collectors.toList());

        //先插入一级目录,过滤已有的一级目录，再进行插入
        ArrayList<String> levelOneList = new ArrayList<>(subjectMap.keySet());
        List<Subject> levelOneSubjectList = CollectionHelper.createArrayList();
        levelOneList.forEach(levelOne->{
                    if (!existLevelOneList.contains(levelOne)){
                        Subject subject = new Subject();
                        subject.setTitle(levelOne);
                        subject.setParentId("0");
                        levelOneSubjectList.add(subject);
                    } });
        subjectService.saveBatch(levelOneSubjectList);

        //重新获取最新的目录数据
        List<Subject> latestSubjectList = subjectService.list();
        //要插入的二级目录
        List<Subject> levelTwoSubjectList = CollectionHelper.createArrayList();
        //根据一级目录的id设置二级目录的parent_id
        subjectMap.entrySet().stream().forEach(entry->{
            String key = entry.getKey();
            List<String> levelTwoList = entry.getValue();
            Subject parentSubject = latestSubjectList.stream()
                    .filter(subject -> subject.getTitle().equals(key)).collect(Collectors.toList()).get(0);
            //过滤重复数据，应用场景为后台，效率低些没关系
            String id = parentSubject.getId();
            List<String> existLevelTwoData = getExistLevelTwoData(latestSubjectList, id);
            levelTwoList.forEach(levelTwo->{
                if (!existLevelTwoData.contains(levelTwo)){
                Subject subject = new Subject();
                subject.setParentId(id);
                subject.setTitle(levelTwo);
                levelTwoSubjectList.add(subject);
                }
            });
        });
        subjectService.saveBatch(levelTwoSubjectList);
    }

    private List<String> getExistLevelTwoData(List<Subject> latestSubjectList,String parentId){
        return latestSubjectList.stream()
                .filter(subject -> parentId.equals(subject.getParentId()))
                .map(Subject::getTitle)
                .collect(Collectors.toList());
    }
}
