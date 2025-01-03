package dev.mvc.storec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/th/storec")
public class StorecCont {

    @Autowired
    private StorecProcInter storecProc;

 // 음식점 리스트
    @GetMapping("/list")
    public String list(Model model) {
        try {
            List<StorecVO> storecList = storecProc.listAll();
            if (storecList == null || storecList.isEmpty()) {
                model.addAttribute("message", "등록된 음식점이 없습니다.");
            } else {
                model.addAttribute("storecList", storecList);
            }
        } catch (Exception e) {
            model.addAttribute("error", "리스트를 불러오는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return "/th/storec/list";
    }


    // 음식점 상세 조회
    @GetMapping("/read/{storecno}")
    public String read(@PathVariable("storecno") int storecno, Model model) {
        try {
            StorecVO storec = storecProc.read(storecno);
            model.addAttribute("storec", storec);
        } catch (Exception e) {
            model.addAttribute("error", "음식점 정보를 불러오는 중 오류가 발생했습니다.");
        }
        return "/th/storec/read";
    }

    // 음식점 등록 페이지
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("storecVO", new StorecVO());
        return "/th/storec/create";
    }

 // 음식점 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute StorecVO storecVO, RedirectAttributes redirectAttributes) {

        // 업로드 디렉토리 설정
        String uploadDir = Storec.getUploadDir(); // Storec.java의 업로드 경로 메서드 사용
        System.out.println("-> 업로드 경로: " + uploadDir);

        File uploadPath = new File(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        try {
            // MultipartFile 가져오기
            MultipartFile imageFile = storecVO.getImage();

            if (imageFile != null && !imageFile.isEmpty()) {
                // 원본 파일명 가져오기
                String originalFileName = imageFile.getOriginalFilename();
                System.out.println("-> 원본 파일명: " + originalFileName);

                long fileSize = imageFile.getSize();

                // 파일 크기와 업로드 가능한 파일 형식 확인
                if (Tool.checkUploadFile(originalFileName)) {
                    // 고유 파일명 생성 및 파일 저장
                    String savedFileName = Upload.saveFileSpring(imageFile, uploadDir);
                    System.out.println("-> 저장된 파일명: " + savedFileName);

                    // 이미지 파일 여부 확인 및 썸네일 생성
                    String thumbnail = "";
                    if (Tool.isImage(savedFileName)) {
                        thumbnail = Tool.preview(uploadDir, savedFileName, 200, 150); // 썸네일 크기 설정
                        System.out.println("-> 썸네일 파일명: " + thumbnail);
                    }

                    // VO에 저장된 파일 정보 설정
                    storecVO.setImageSaved(originalFileName); // 원본 파일명
                    storecVO.setImageSaved(savedFileName); // 저장된 파일명
                    storecVO.setThumb(thumbnail); // 썸네일 파일명
                    storecVO.setSize(fileSize); // 파일 크기
                } else {
                    // 파일 형식이 잘못된 경우 처리
                    redirectAttributes.addFlashAttribute("msg", "invalid_file_format");
                    return "redirect:/th/storec/msg";
                }
            }

            // 데이터베이스에 저장
            storecProc.create(storecVO);
            redirectAttributes.addFlashAttribute("msg", "create_success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "create_fail");
            e.printStackTrace();
        }

        return "redirect:/th/storec/list";
    }




    // 음식점 수정 페이지
    @GetMapping("/update/{storecno}")
    public String updateForm(@PathVariable("storecno") int storecno, Model model) {
        try {
            StorecVO storec = storecProc.read(storecno);
            model.addAttribute("storec", storec);
        } catch (Exception e) {
            model.addAttribute("error", "음식점 정보를 불러오는 중 오류가 발생했습니다.");
        }
        return "/th/storec/update";
    }

    // 음식점 수정 처리

    @PostMapping("/update")
    public String update(@ModelAttribute StorecVO storecVO, RedirectAttributes redirectAttributes) {
        String uploadDir = Storec.getUploadDir(); // 업로드 경로
        System.out.println("-> 업로드 경로: " + uploadDir);

        MultipartFile imageFile = storecVO.getImage();
        String originalFileName = (imageFile != null) ? imageFile.getOriginalFilename() : null;
        long fileSize = (imageFile != null) ? imageFile.getSize() : 0;

        try {
            // 기존 데이터 조회 (기존 파일 삭제를 위해 필요)
            StorecVO existingStorec = storecProc.read(storecVO.getStorecno());

            if (fileSize > 0) {
                // 파일 검증 및 저장
                if (Tool.checkUploadFile(originalFileName)) {
                    // 파일 저장
                    String savedFileName = Upload.saveFileSpring(imageFile, uploadDir);

                    // 이미지 파일 여부 확인 및 썸네일 생성
                    String thumbnail = "";
                    if (Tool.isImage(savedFileName)) {
                        thumbnail = Tool.preview(uploadDir, savedFileName, 200, 150);
                    }

                    // 기존 파일 삭제
                    if (existingStorec.getImageSaved() != null && !existingStorec.getImageSaved().isEmpty()) {
                        File savedFile = new File(uploadDir + existingStorec.getImageSaved());
                        if (savedFile.exists() && savedFile.isFile()) {
                            savedFile.delete();
                        }
                    }
                    if (existingStorec.getThumb() != null && !existingStorec.getThumb().isEmpty()) {
                        File thumbFile = new File(uploadDir + existingStorec.getThumb());
                        if (thumbFile.exists() && thumbFile.isFile()) {
                            thumbFile.delete();
                        }
                    }

                    // 새 파일 정보 설정
                    storecVO.setImageSaved(originalFileName); // 원본 파일명
                    storecVO.setImageSaved(savedFileName); // 저장된 파일명
                    storecVO.setThumb(thumbnail); // 썸네일
                    storecVO.setSize(fileSize); // 파일 크기
                } else {
                    redirectAttributes.addFlashAttribute("msg", "invalid_file_format");
                    return "redirect:/th/storec/msg";
                }
            } else {
                // 파일을 업로드하지 않은 경우, 기존 파일 정보 유지
                storecVO.setImage(existingStorec.getImage());
                storecVO.setImageSaved(existingStorec.getImageSaved());
                storecVO.setThumb(existingStorec.getThumb());
                storecVO.setSize(existingStorec.getSize());
            }

            // 데이터베이스 업데이트
            storecProc.update(storecVO);
            redirectAttributes.addFlashAttribute("msg", "update_success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "update_fail");
            e.printStackTrace();
        }

        return "redirect:/th/storec/list"; // 수정 후 리스트로 이동
    }



    // 음식점 삭제
    @GetMapping("/delete/{storecno}")
    public String delete(@PathVariable("storecno") int storecno, RedirectAttributes redirectAttributes) {
        try {
            storecProc.delete(storecno);
            redirectAttributes.addFlashAttribute("msg", "delete_success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "delete_fail");
            e.printStackTrace();
        }
        return "redirect:/th/storec/list";
    }

    // 카테고리별 음식점 리스트
    @GetMapping("/listByCategory/{itemno}")
    public String listByCategory(@PathVariable("itemno") int itemno, Model model) {
        try {
            List<StorecVO> storecList = storecProc.listByItemno(itemno);
            model.addAttribute("storecList", storecList);
        } catch (Exception e) {
            model.addAttribute("error", "카테고리별 리스트를 불러오는 중 오류가 발생했습니다.");
        }
        return "/th/storec/list";
    }

    // 검색
    @GetMapping("/search")
    public String search(@RequestParam Map<String, Object> params, Model model) {
        try {
            List<StorecVO> storecList = storecProc.search(params);
            model.addAttribute("storecList", storecList);
        } catch (Exception e) {
            model.addAttribute("error", "검색 결과를 불러오는 중 오류가 발생했습니다.");
        }
        return "/th/storec/list";
    }
}
