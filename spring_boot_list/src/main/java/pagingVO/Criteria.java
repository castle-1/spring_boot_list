package pagingVO;

public class Criteria {

	private int amount;
	private int pageNum;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) { // 파라미터 순서 중요
		this.amount = amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
