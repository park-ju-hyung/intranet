package initech.mvc.service.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import initech.common.util.AppPagingUtil;
import initech.mvc.dto.OnlineDTO;
import initech.mvc.mapper.OnlineMapper;
import initech.mvc.vo.OnlineVO;

@Transactional
@Service
public class OnlineService {
	
	@Autowired
	private OnlineMapper onlineMapper;
	
	
    // 수요자 > 게시판 > 저장
    public void userSave(OnlineDTO onlineDTO) throws Exception{    	
        onlineMapper.insertOnline(onlineDTO);
    }
    
}
