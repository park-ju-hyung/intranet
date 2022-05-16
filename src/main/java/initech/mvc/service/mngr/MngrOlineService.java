package initech.mvc.service.mngr;

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
public class MngrOlineService {

	
	@Autowired
	private OnlineMapper onlineMapper;
	
	// 관리자 > 게시판 > 전체조회
	public Map<String, Object> mngrFindAll(OnlineDTO onlineDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		
		long pageNo = onlineDTO.getPageNo() == null ? 1 : onlineDTO.getPageNo();
		long pageSize = onlineDTO.getPageSize() == null ? 10 : onlineDTO.getPageSize();
		long pageBlock = onlineDTO.getPageBlock() == null ? 10 : onlineDTO.getPageBlock();
		onlineDTO.setPageNo(pageNo);
		onlineDTO.setPageSize(pageSize);
		onlineDTO.setPageBlock(pageBlock);
		onlineDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));

		List<OnlineVO> list = onlineMapper.selectOnlineListPaging(onlineDTO);
		
		if (pageNo != 1 && list.size() == 0) {
			pageNo = 1;
			onlineDTO.setPageNo(pageNo);
			onlineDTO.setPageOffset(AppPagingUtil.getOffset(pageNo, pageSize));
			list = onlineMapper.selectOnlineListPaging(onlineDTO);
		}

		long totalCount = onlineMapper.selectOnlineTotalCount();
		long totalPageNo = AppPagingUtil.getTotalPageNo(totalCount, pageSize);
		String pagingHtml = AppPagingUtil.getMngrPagingHtml(totalCount, pageNo, pageSize, pageBlock);
		
		rs.put("onlineDTO", onlineDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);
		rs.put("totalPageNo", totalPageNo);
		rs.put("pagingHtml", pagingHtml);
		return rs;
	}
	
	
	// 관리자 > 게시판 > 조회
	public Map<String, Object> mngrFind(OnlineDTO onlineDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		OnlineVO onlineVo = onlineMapper.selectOnline(onlineDTO);

		rs.put("onlineDTO", onlineDTO);
		rs.put("onlineVO", onlineVo);

		return rs;
	}
	
	// 관리자 > 게시판 > 삭제
    public void mngrRemove(OnlineDTO onlineDTO) throws Exception{
    	onlineMapper.deleteOnline(onlineDTO);
    }
    
    
}
