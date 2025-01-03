// src/main/java/dev/mvc/survey/SurveyCont.java
package dev.mvc.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/th/survey")
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

        return "/th/survey/list";
    }

    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("surveyVO", new SurveyVO());
        return "/th/survey/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SurveyVO surveyVO) {

        String poster = "";
        String postersaved = "";
        String posterthumb = "";

        // Survey.java에서 정의한 업로드 경로 사용
        String upDir = Survey.getUploadDir();
        System.out.println("-> upDir: " + upDir);


        MultipartFile mf = surveyVO.getPosterFile();
        poster = mf.getOriginalFilename();
        System.out.println("-> 원본 파일명: " + poster);
        long postersize= mf.getSize();
        if (postersize > 0) {
          if (Tool.checkUploadFile(poster) == true) {
            postersaved = Upload.saveFileSpring(mf, upDir);

            if (Tool.isImage(postersaved)) {
              posterthumb = Tool.preview(upDir, postersaved, 200, 150);
            }

            surveyVO.setPoster(poster);
            surveyVO.setPostersaved(postersaved);
            surveyVO.setPosterthumb(posterthumb);
            surveyVO.setPostersize(postersize);
            
          } else { 
            return "redirect:/th/survey/msg"; 
          }
          
       }
        
      

            
            



       this.surveyProc.create(surveyVO); // DB에 저장
            

        return "redirect:/th/survey/list"; // 파일 처리 후 리스트로 이동
    }




    @GetMapping("/read/{surveyno}")
    public String read(@PathVariable int surveyno, Model model) {
        model.addAttribute("survey", surveyProc.read(surveyno));
        return "/th/survey/read";
    }
    
    @GetMapping("/update/{surveyno}")
    public String updateForm(@PathVariable("surveyno") int surveyno, Model model) {
        SurveyVO surveyVO = surveyProc.read(surveyno);
        model.addAttribute("surveyVO", surveyVO);
        return "/th/survey/update";
    }



    @PostMapping("/update")
    public String update(@ModelAttribute SurveyVO surveyVO) {

        String poster = "";
        String postersaved = "";
        String posterthumb = "";

        // Survey.java에서 정의한 업로드 경로 사용
        String upDir = Survey.getUploadDir();
        System.out.println("-> upDir: " + upDir);

        MultipartFile mf = surveyVO.getPosterFile();
        poster = mf.getOriginalFilename();
        System.out.println("-> 원본 파일명: " + poster);
        long postersize = mf.getSize();

        // 기존 데이터 조회 (기존 파일 삭제를 위해 필요)
        SurveyVO existingSurvey = surveyProc.read(surveyVO.getSurveyno());

        if (postersize > 0) {
            if (Tool.checkUploadFile(poster)) {
                postersaved = Upload.saveFileSpring(mf, upDir);

                if (Tool.isImage(postersaved)) {
                    posterthumb = Tool.preview(upDir, postersaved, 200, 150);
                }

                // 기존 파일 삭제
                if (existingSurvey.getPostersaved() != null && !existingSurvey.getPostersaved().isEmpty()) {
                    File savedFile = new File(upDir + existingSurvey.getPostersaved());
                    if (savedFile.exists() && savedFile.isFile()) {
                        savedFile.delete();
                    }
                }

                if (existingSurvey.getPosterthumb() != null && !existingSurvey.getPosterthumb().isEmpty()) {
                    File thumbFile = new File(upDir + existingSurvey.getPosterthumb());
                    if (thumbFile.exists() && thumbFile.isFile()) {
                        thumbFile.delete();
                    }
                }

                surveyVO.setPoster(poster);
                surveyVO.setPostersaved(postersaved);
                surveyVO.setPosterthumb(posterthumb);
                surveyVO.setPostersize(postersize);
            } else {
                return "redirect:/th/survey/msg";
            }
        } else {
            // 파일을 업로드하지 않은 경우, 기존 파일 정보를 유지
            surveyVO.setPoster(existingSurvey.getPoster());
            surveyVO.setPostersaved(existingSurvey.getPostersaved());
            surveyVO.setPosterthumb(existingSurvey.getPosterthumb());
            surveyVO.setPostersize(existingSurvey.getPostersize());
        }

        surveyProc.update(surveyVO); // 데이터베이스 업데이트

        return "redirect:/th/survey/list"; // 파일 처리 후 리스트로 이동
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

        return "/th/survey/list"; // 기존 리스트 페이지 재사용
    }
    
    
    @GetMapping("/delete/{surveyno}")
    public String deleteForm(@PathVariable("surveyno") int surveyno, Model model) {
        SurveyVO surveyVO = surveyProc.read(surveyno); // 설문조사 데이터 읽기
        model.addAttribute("surveyVO", surveyVO);
        return "/th/survey/delete"; // delete.html로 이동
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("surveyno") int surveyno) {
        surveyProc.delete(surveyno); // 설문조사 삭제
        return "redirect:/th/survey/list";
    }

}
