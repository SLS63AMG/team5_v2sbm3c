// src/main/java/dev/mvc/survey/SurveyCont.java
package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/survey")
public class SurveyCont {

    @Autowired
    private SurveyProcInter surveyProc;

    @GetMapping("/list")
    public String list(
            @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
            Model model) {
        int recordPerPage = 3; // 페이지당 3개 항목
        int totalRecords = surveyProc.count(); // 전체 설문조사 수
        int totalPage = (int) Math.ceil((double) totalRecords / recordPerPage);

        // 현재 페이지의 시작과 끝 행 계산
        int startRow = (nowPage - 1) * recordPerPage + 1;
        int endRow = nowPage * recordPerPage;

        // 파라미터 설정
        Map<String, Object> map = new HashMap<>();
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        // 데이터 가져오기
        List<SurveyVO> list = surveyProc.list_by_page(map);
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);

        return "survey/list";
    }

    
    @GetMapping("/create") // 등록 페이지 이동 처리
    public String createForm(Model model) {
        model.addAttribute("surveyVO", new SurveyVO());
        return "survey/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SurveyVO surveyVO) {
        MultipartFile file = surveyVO.getFile1MF(); // 업로드된 파일 가져오기
        if (file != null && !file.isEmpty()) {
            try {
                // 업로드 경로 설정
                String uploadPath = "C:/kd/team5/uploads/";
                String fileName = file.getOriginalFilename(); // 원본 파일 이름
                File saveFile = new File(uploadPath, fileName);

                // 디렉토리가 없으면 생성
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }

                // 파일 저장
                file.transferTo(saveFile);

                // VO에 파일 이름 설정
                surveyVO.setPosterthumb(fileName); // 썸네일 필드에 파일 이름 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 데이터베이스에 저장
        surveyProc.create(surveyVO);
        return "redirect:/survey/list";
    }

    @GetMapping("/read/{surveyno}")
    public String read(@PathVariable int surveyno, Model model) {
        model.addAttribute("survey", surveyProc.read(surveyno));
        return "survey/read";
    }
    
    @GetMapping("/update/{surveyno}")
    public String updateForm(@PathVariable("surveyno") int surveyno, Model model) {
        SurveyVO surveyVO = surveyProc.read(surveyno);
        model.addAttribute("surveyVO", surveyVO);
        return "survey/update";
    }



    @PostMapping("/update")
    public String update(SurveyVO surveyVO) {
        MultipartFile file = surveyVO.getFile1MF(); // 파일 업로드 처리
        if (file != null && !file.isEmpty()) {
            try {
                String uploadPath = "C:/kd/team5/uploads/";
                String fileName = file.getOriginalFilename();
                File saveFile = new File(uploadPath, fileName);

                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }

                file.transferTo(saveFile);
                surveyVO.setPosterthumb(fileName); // 썸네일 경로 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        surveyProc.update(surveyVO); // 수정된 데이터 저장
        return "redirect:/survey/list";
    }

    @GetMapping("/delete/{surveyno}")
    public String deleteForm(@PathVariable("surveyno") int surveyno, Model model) {
        SurveyVO surveyVO = surveyProc.read(surveyno); // 설문조사 데이터 읽기
        model.addAttribute("surveyVO", surveyVO);
        return "survey/delete"; // delete.html로 이동
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("surveyno") int surveyno) {
        surveyProc.delete(surveyno); // 설문조사 삭제
        return "redirect:/survey/list";
    }
    
    @GetMapping("/search")
    public String search(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
            Model model) {
        int recordPerPage = 3; // 페이지당 3개 항목
        int totalRecords = surveyProc.searchCount(keyword); // 검색 결과 전체 개수
        int totalPage = (int) Math.ceil((double) totalRecords / recordPerPage);

        // 현재 페이지의 시작과 끝 행 계산
        int startRow = (nowPage - 1) * recordPerPage + 1;
        int endRow = nowPage * recordPerPage;

        // 검색 파라미터 설정
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("startRow", startRow);
        map.put("endRow", endRow);

        // 검색 결과 가져오기
        List<SurveyVO> searchResults = surveyProc.searchByPage(map);
        model.addAttribute("list", searchResults);
        model.addAttribute("keyword", keyword); // 검색어를 뷰로 전달
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("totalPage", totalPage);

        return "survey/list"; // 기존 리스트 페이지 재사용
    }

}
