package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.contents.ContentsProc")
public class ContentsProc implements ContentsProcInter {
    
    @Autowired
    private ContentsDAOInter contentsDAO;

    @Override
    public int create(ContentsVO contentsVO) {
        return contentsDAO.create(contentsVO);
    }

    @Override
    public ArrayList<ContentsVO> list_all() {
        return contentsDAO.list_all();
    }

    @Override
    public ArrayList<ContentsVO> list_by_cateno(int cateno) {
        return contentsDAO.list_by_cateno(cateno);
    }

    @Override
    public ContentsVO read(int contentsno) {
        return contentsDAO.read(contentsno);
    }

    @Override
    public int update_text(ContentsVO contentsVO) {
        return contentsDAO.update_text(contentsVO);
    }

    @Override
    public int delete(int contentsno) {
        return contentsDAO.delete(contentsno);
    }

    @Override
    public String pagingBox(int cateno, int now_page, int totalPage, String list_file) {
        StringBuilder paging = new StringBuilder();
        int pagePerBlock = 10;
        int startPage = (now_page - 1) / pagePerBlock * pagePerBlock + 1;
        int endPage = Math.min(startPage + pagePerBlock - 1, totalPage);

        paging.append("<div>");
        if (startPage > pagePerBlock) {
            paging.append("<a href='").append(list_file).append("?now_page=").append(startPage - 1).append("'>이전</a>");
        }
        for (int i = startPage; i <= endPage; i++) {
            paging.append("<a href='").append(list_file).append("?now_page=").append(i).append("'>").append(i).append("</a>");
        }
        if (endPage < totalPage) {
            paging.append("<a href='").append(list_file).append("?now_page=").append(endPage + 1).append("'>다음</a>");
        }
        paging.append("</div>");

        return paging.toString();
    }
}
