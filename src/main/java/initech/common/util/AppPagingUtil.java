package initech.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppPagingUtil {
	// 페이징 전체 페이지 번호
	public static long getTotalPageNo(long totalCount, long pageSize) {
		long totalPageNo = 1;
		if (totalCount > 0 && pageSize > 0) {
			totalPageNo = (long) Math.ceil((double) totalCount / pageSize);
		}
		return totalPageNo;
	}

	// 페이징 offset(시작 번호) 구하기
	public static long getOffset(long pageNo, long pageSize) {
		return pageSize * (pageNo - 1);
	}

	// 페이징 끝 번호 구하기
	public static long getEndNo(long pageNo, long pageSize) {
		return getOffset(pageNo, pageSize) + pageSize;
	}

	// 관리자 페이징 html
	public static String getMngrPagingHtml(long totalCount, long pageNo, long pageSize, long pageBlock) {

		// totalCount : 게시글의 총 개수
		// pageNo : 현재 페이지번호
		// pageSize : 페이지크기
		// pageBlock : 한 페이지당 나와야하는 게시글의 개수
		
		StringBuffer rs = new StringBuffer();

		long totalPage = getTotalPageNo(totalCount, pageSize);

		if (totalCount > 0) {
			// 현재 페이지 계산
			long startPage = 1;
			if (pageNo > 0 && pageBlock > 0) {
				if (pageNo % pageBlock > 0) {
					startPage = pageNo - (pageNo % pageBlock) + 1;
				} else {
					startPage = pageNo - pageBlock + 1;
				}
			}

			// 마지막 페이지 계산
			long endPage = startPage + pageBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage; // 마지막 페이지가 총 페이지 수 보다 크면 총 페이지로 셋팅
			}

			String prevActive = "";
			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				prevActive = "disabled";
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}
			
			rs.setLength(0);

			/*
                            <div class="pagination paging">
                                <a class="prev" alt="이전페이지"></a>
                                <a href="#1^10" data-pg="1">1</a>
                                <a href="#2^10" data-pg="2">2</a>
                                <a href="#3^10" data-pg="3">3</a>
                                <a href="#4^10" data-pg="4">4</a>
                                <a href="#5^10" data-pg="5">5</a>
                                <a href="#6^10" data-pg="6">6</a>
                                <a href="#7^10" data-pg="7">7</a>
                                <a href="#8^10" data-pg="8">8</a>
                                <a href="#9^10" data-pg="9">9</a>
                                <a href="#10^10" data-pg="10">10</a>
                                <a class="next" href="#11^10">다음</a>
                            </div>
			 */
			
			rs.append("<div class=\"pagination paging\" id=\"paging\">	\n");

			// 이전
			rs.append("<a href=\"#\" class=\"prev page-link" + prevActive + "\" data-pageNo=\"" + beforePage + "\"></a>");
			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				if (pageNo == i) {
					rs.append("<a href=\"#\" class=\"data-pg active\">" + i + "</a>");
				} else {
					rs.append("<a href=\"#\" class=\"page-link\" data-pageNo=\"" + i + "\">" + i + "</a>");
				}
			}
			// 다음
			rs.append("<a href=\"#\" class=\"next page-link" + nextActive + "\" data-pageNo=\"" + afterPage
					+ "\"></a> ");
			
			rs.append("</div>");
		}

		return rs.toString();
	}

}
