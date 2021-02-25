package pagingVO;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;


public class PageVO {
	
	private int startPage;
	private int endPage;
	private int total;
	private Criteria cri;
	private boolean prev;
	private boolean next;
	
	public PageVO(Criteria cri,int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = endPage - 9;
		
		int realEnd = (int)( Math.ceil( (total*1.0)/cri.getAmount() ) );
		if(realEnd <=this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage>1;
		this.next = this.endPage<realEnd;
		
	}
	
	public String makeQuery(int page) {
		UriComponents components = UriComponentsBuilder.newInstance().queryParam("pageNum", page).
				queryParam("amount", cri.getAmount()).build();
		
		return components.toUriString();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	
	

}
