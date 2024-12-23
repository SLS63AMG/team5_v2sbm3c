package dev.mvc.cate;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dev.mvc.cate.CateDAO")
public class CateDAO implements CateDAOInter {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private static String namespace = "dev.mvc.cate.CateMapper";

    @Override
    public int create(CateVO cateVO) {
        return sqlSession.insert(namespace + ".create", cateVO);
    }

    @Override
    public List<CateVO> list_all() {
        return sqlSession.selectList(namespace + ".list_all");
    }

    @Override
    public CateVO read(int cateno) {
        return sqlSession.selectOne(namespace + ".read", cateno);
    }

    @Override
    public int update(CateVO cateVO) {
        return sqlSession.update(namespace + ".update", cateVO);
    }

    @Override
    public int delete(int cateno) {
        return sqlSession.delete(namespace + ".delete", cateno);
    }
}
