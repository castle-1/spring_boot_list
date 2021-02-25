package edu.bit.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.bit.ex.vo.BoardVO;
import pagingVO.Criteria;

@Mapper
public interface BoardMapper {

	

	public List<BoardVO> getlist();

	public List<BoardVO> pagingList(Criteria cri);

	public int getTotal(Criteria cri);

	public BoardVO getBoard(int getbId);

	public void delete(int getbId);

	public void modify(BoardVO vo);

	public BoardVO replyView(int getbId);

	public void reply(BoardVO vo);

	public void replyShape(BoardVO vo);

	public void write(BoardVO vo);

}
