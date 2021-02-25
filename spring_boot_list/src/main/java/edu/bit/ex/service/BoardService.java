package edu.bit.ex.service;

import java.util.List;

import edu.bit.ex.vo.BoardVO;
import pagingVO.Criteria;

public interface BoardService { //인터페이스 - 기능의 구현을 강제시킨다. 또한 마크업기능
	
	public List<BoardVO> getlist();

	public List<BoardVO> getlist(Criteria cri);

	public int getTotal(Criteria cri);

	public BoardVO getBoard(int getbId);

	public void delete(int getbId);

	public void modify(BoardVO vo);

	public BoardVO replyView(int getbId);

	public void reply(BoardVO vo);

	public void write(BoardVO vo);

	

}
