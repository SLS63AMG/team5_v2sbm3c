package dev.mvc.contents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsProc implements ContentsProcInter {

    @Autowired
    private ContentsDAOInter contentsDAO;

    @Override
    public int create(ContentsVO contentsVO) {
        return contentsDAO.create(contentsVO);
    }

    @Override
    public List<ContentsVO> list() {
        return contentsDAO.list();
    }

    @Override
    public ContentsVO read(int contentsno) {
        return contentsDAO.read(contentsno);
    }

    @Override
    public int update(ContentsVO contentsVO) {
        return contentsDAO.update(contentsVO);
    }

    @Override
    public int delete(int contentsno) {
        return contentsDAO.delete(contentsno);
    }
}
