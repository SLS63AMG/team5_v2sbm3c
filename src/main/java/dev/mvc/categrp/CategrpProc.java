package dev.mvc.categrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.categrp.CategrpProc")
public class CategrpProc implements CategrpProcInter {
    @Autowired // CategrpDAOInter를 구현한 클래스의 객체를 자동으로 주입
    private CategrpDAOInter categrpDAO;

    public CategrpProc() {
        System.out.println("-> CategrpProc created.");
    }

    @Override
    public int create(CategrpVO categrpVO) {
        int cnt = this.categrpDAO.create(categrpVO);
        return cnt;
    }

    @Override
    public ArrayList<CategrpVO> list_all() {
        ArrayList<CategrpVO> list = this.categrpDAO.list_all();
        return list;
    }

    @Override
    public CategrpVO read(int categrpno) {
        CategrpVO categrpVO = this.categrpDAO.read(categrpno);
        return categrpVO;
    }

    @Override
    public int update(CategrpVO categrpVO) {
        int cnt = this.categrpDAO.update(categrpVO);
        return cnt;
    }

    @Override
    public int delete(int categrpno) {
        int cnt = this.categrpDAO.delete(categrpno);
        return cnt;
    }

    @Override
    public ArrayList<CategrpVO> search(String keyword) {
        ArrayList<CategrpVO> list = this.categrpDAO.search(keyword);
        return list;
    }

    @Override
    public String pagingBox(int now_page, String list_file_name, int total_count, 
                            int record_per_page, int page_per_block) {
        int total_page = (int)(Math.ceil((double)total_count / record_per_page));
        int total_grp = (int)(Math.ceil((double)total_page / page_per_block));
        int now_grp = (int)(Math.ceil((double)now_page / page_per_block));

        int start_page = ((now_grp - 1) * page_per_block) + 1;
        int end_page = now_grp * page_per_block;

        StringBuffer str = new StringBuffer();
        str.append("<style type='text/css'>");
        str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
        str.append("  .span_box {padding: 5px; margin: 2px; border: 1px solid #cccccc;}");
        str.append("  .current_page {font-weight: bold; background-color: #dddddd;}");
        str.append("</style>");
        str.append("<div id='paging'>");

        if (now_grp > 1) {
            int prev_page = (now_grp - 1) * page_per_block;
            str.append("<span class='span_box'><a href='" + list_file_name + "?now_page=" + prev_page + "'>이전</a></span>");
        }

        for (int i = start_page; i <= end_page; i++) {
            if (i > total_page) break;

            if (i == now_page) {
                str.append("<span class='span_box current_page'>" + i + "</span>");
            } else {
                str.append("<span class='span_box'><a href='" + list_file_name + "?now_page=" + i + "'>" + i + "</a></span>");
            }
        }

        if (now_grp < total_grp) {
            int next_page = now_grp * page_per_block + 1;
            str.append("<span class='span_box'><a href='" + list_file_name + "?now_page=" + next_page + "'>다음</a></span>");
        }

        str.append("</div>");
        return str.toString();
    }

    @Override
    public void incrementCategrpCount(int categrpno) {
        this.categrpDAO.incrementCategrpCount(categrpno);
    }

    @Override
    public void decrementCategrpCount(int categrpno) {
        this.categrpDAO.decrementCategrpCount(categrpno);
    }
}