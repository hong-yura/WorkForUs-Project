package site.workforus.forus.board.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PagingDTO {

	private int offset;
	private int limit;
	private List<Integer> pages;	// 총 몇 페이지인지 
	private List<Object> pageDatas; // 한 페이지에 들어갈 객체
	private int currentPage;
	private int nextPage;
	private int prevPage;
	
	public PagingDTO() {}
	
	public PagingDTO(int page, int limit, int totalRows) {
		this.offset = limit * (page - 1); // row수에서 현재 내 페이지 - 1을 한 값을 offset으로 설정 -> 그럼 만약 내가 현재 1페이지라면 offset은 0인 거,,
		this.limit = limit;	// 내가 몇 페이지까지 원하는지 -> limit가 10이면 아마 
		this.currentPage = page;
		this.nextPage = page + 1;
		this.prevPage = page - 1;
		this.pages = new ArrayList<Integer>();
		page = 1;
		for(int i = 0; i < totalRows; i+=limit) {
			this.pages.add(page++);
		}
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public List<Integer> getPages() { 
		return pages;
	}
	
	public List<Integer> getPages(int start, int end) { // 시작 번호, 끝 번호 -> 페이지 리스트 중에서 4~9까지만 보이도록 할 수 있다.
		start = start < 1 ? 1 : start; 
		end = end > pages.size() ? pages.size() : end; // end가 page리스트보다 수가 커지면 pages.size()로 설정
		return pages.subList(start - 1, end);
	}
	
	public List<Object> getPageDatas() {
		return pageDatas;
	}
	
	public void setPageDatas(Iterator<Object> iter) {
		this.pageDatas = new ArrayList<Object>();
		while(iter.hasNext()) {
			pageDatas.add(iter.next());
		}
		
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getNextPage() {
		return nextPage;
	}
	
	public int getPrevPage() {
		return prevPage;
	}
	
	public boolean hasNextPage() { // 다음 페이지가 있는지 확인
		//return this.nextPage > this.pages.size() / limit; // size가 10이면 실제 페이지는 11페이지임
		return this.pages.contains(this.nextPage);
	}
	
	public boolean hasPrevPage() { // 이전 페이지가 있는지 확인
		return this.prevPage <= 0 ? false : true;
		//return this.pages.contains(this.prevPage);
	}
	
}
