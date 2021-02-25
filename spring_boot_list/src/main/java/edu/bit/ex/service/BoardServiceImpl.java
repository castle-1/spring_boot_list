package edu.bit.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import pagingVO.Criteria;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getlist() {
		
		return mapper.getlist();
	}

	@Override
	public List<BoardVO> getlist(Criteria cri) {
		
		return mapper.pagingList(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return mapper.getTotal(cri);
	}

	@Override
	public BoardVO getBoard(int getbId) {
		// TODO Auto-generated method stub
		return mapper.getBoard(getbId);
	}

	@Override
	public void delete(int getbId) {
		// TODO Auto-generated method stub
		mapper.delete(getbId);
		
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.modify(vo);
		
	}

	@Override
	public BoardVO replyView(int getbId) {
		// TODO Auto-generated method stub
		return mapper.replyView(getbId);
	}

	@Override
	public void reply(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.reply(vo);
		mapper.replyShape(vo);
	}

	@Override
	public void write(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.write(vo);
		
	}

}
