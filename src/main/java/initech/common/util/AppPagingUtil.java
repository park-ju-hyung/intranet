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

			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}
			
			String prevActive = "";
			if (beforePage == 1) {
				prevActive = "disabled";
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}
			
			rs.setLength(0);

			rs.append("<div class=\"dataTables_paginate paging_simple_numbers\" id=\"dataTable_paginate\">	\n");
			rs.append("	<ul class=\"pagination\">	\n");

			// 맨처음
			//rs.append("     <li class=\"page-item\"><a class=\"page-link\" href=\"#\" data-pageNo=\"1\"><i class=\"fas fa-angle-double-left\"></i></a></li>");
			
			// 이전
			rs.append("     <li class=\"paginate_button page-item previous " + prevActive + " \"><a class=\"page-link\" href=\"#\" data-pageNo=\"" + beforePage
					+ "\">Prev</a></li>");
			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				if (pageNo == i) {
					rs.append("     <li class=\"paginate_button page-item active\"><span class=\"page-link\">" + i + "</span></li>");
				} else {
					rs.append("		<li class=\"paginate_button page-item\"><a class=\"page-link\" href=\"#\" data-pageNo=\"" + i
							+ "\">" + i + "</a></li>");
				}
			}

			// 다음
			rs.append("		<li class=\"paginate_button page-item next " + nextActive + " \"><a class=\"page-link\" href=\"#\" data-pageNo=\"" + afterPage 
					+ "\">Next</a></li>");
			
			// 마지막
			// rs.append(" <li class=\"page-item\"><a class=\"page-link\" href=\"#\"
			// data-pageNo=\"" + totalPage + "\"><i class=\"fas
			// fa-angle-double-right\"></i></a></li>");

			rs.append("	</ul>");
			rs.append("</div>");
		}

		return rs.toString();
	}

	// 사이트 페이징 html
	public static String getSitePagingHtml(long totalCount, long pageNo, long pageSize, long pageBlock) {

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

			// 맨처음, 이전 글
			long beforePage = startPage - pageBlock;
			if (beforePage < 1) {
				beforePage = 1;
			}

			// 맨마지막, 다음 글
			long afterPage = startPage + pageBlock;
			if (afterPage > totalPage) {
				afterPage = totalPage;
			}
			
			String prevActive = "";
			if (beforePage == 1) {
				prevActive = "disabled";
			}

			String nextActive = "";
			if ((startPage + pageBlock) > totalPage) {
				nextActive = "disabled";
			}

			rs.setLength(0);

			rs.append("<div class=\"pagination\" id=\"paging\">	\n");

			// 맨처음
			rs.append("	<a class=\"page-link pprev\" href=\"#\" data-pageNo=\"1\">처음</a>");

			// 이전
			rs.append("		<a href=\"#\" class=\"page-link prev " + prevActive + "\" data-pageNo=\"" + beforePage
					+ "\">이전</a>");

			// 이동 페이지
			for (long i = startPage; i <= endPage; i++) {
				if (pageNo == i) {
					rs.append("		<a href=\"#\" class=\"choice\">" + i + "</a>");
				} else {
					rs.append("		<a href=\"#\" class=\"page-link\" data-pageNo=\"" + i + "\">" + i + "</a>");
				}
			}

			// 다음
			rs.append("		<a href=\"#\" class=\"page-link next " + prevActive + "\" data-pageNo=\"" + afterPage
					+ "\">다음</a> ");

			// 마지막
			rs.append("	<a class=\"page-link nnext\" href=\"#\" data-pageNo=\""+totalPage+"\"></a>");

			rs.append("</div>");
		}

		return rs.toString();
	}
}
