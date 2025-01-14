package dev.mvc.storegood;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.storegood.StoregoodProc")
public class StoregoodProc implements StoregoodProcInter {

    @Autowired
    private StoregoodDAOInter storegoodDAO;
    

    @Override
    public int create(StoregoodVO storegoodVO) {
        return this.storegoodDAO.create(storegoodVO);
    }

    @Override
    public ArrayList<StoregoodVO> list_all() {
        return this.storegoodDAO.list_all();
    }

    @Override
    public int delete(int storegoodno) {
        return this.storegoodDAO.delete(storegoodno);
    }

    @Override
    public int hartCnt(HashMap<String, Object> map) {
        return this.storegoodDAO.hartCnt(map);
    }
    
    @Override
    public StoregoodVO read(int Storegoodno) {
        return this.storegoodDAO.read(Storegoodno);
    }

    @Override
    public StoregoodVO readByStorenoMemberno(HashMap<String, Object> map) {
        return this.storegoodDAO.readByStorenoMemberno(map);
    }
   
}
