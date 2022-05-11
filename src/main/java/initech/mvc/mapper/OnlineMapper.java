package initech.mvc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import initech.common.config.DBMapper;
import initech.mvc.dto.OnlineDTO;
import initech.mvc.vo.OnlineVO;

@DBMapper
@Repository
public interface OnlineMapper {
	
	//	리스트 페이징
    public List<OnlineVO> selectOnlineListPaging(OnlineDTO onlineDTO) throws Exception;
    
    //  건수조회
    public long selectOnlineTotalCount() throws Exception;
    
    //  조회
    public OnlineVO selectOnline(OnlineDTO onlineDTO) throws Exception;
    
    //  입력
    public void insertOnline(OnlineDTO onlineDTO) throws Exception;
    
    //  삭제
    public void deleteOnline(OnlineDTO onlineDTO) throws Exception;
}
