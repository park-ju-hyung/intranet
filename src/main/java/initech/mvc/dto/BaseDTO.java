package initech.mvc.dto;

public class BaseDTO {
	//페이징
	private Long pageNo = 1L;//페이지 번호
	private Long pageSize = 10L;//페이지 사이즈
	private Long pageBlock = 10L;//페이지 블록
	private Long pageOffset;//페이징 범위
	
	private Long pageNo2 = 1L;//페이지 번호
	private Long pageSize2 = 10L;//페이지 사이즈
	private Long pageBlock2 = 10L;//페이지 블록

	//검색
	private String searchField;//검색 필드
	private String searchKwd;//검색 단어
	
	public BaseDTO() {

	}

	public Long getPageNo() {
		return pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(Long pageBlock) {
		this.pageBlock = pageBlock;
	}
	public Long getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(Long pageOffset) {
		this.pageOffset = pageOffset;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchKwd() {
		return searchKwd;
	}
	public void setSearchKwd(String searchKwd) {
		this.searchKwd = searchKwd;
	}
	public Long getPageNo2() {
		return pageNo2;
	}
	public void setPageNo2(Long pageNo2) {
		this.pageNo2 = pageNo2;
	}
	public Long getPageSize2() {
		return pageSize2;
	}
	public void setPageSize2(Long pageSize2) {
		this.pageSize2 = pageSize2;
	}
	public Long getPageBlock2() {
		return pageBlock2;
	}
	public void setPageBlock2(Long pageBlock2) {
		this.pageBlock2 = pageBlock2;
	}
}
