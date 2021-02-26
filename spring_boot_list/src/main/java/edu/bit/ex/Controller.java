package edu.bit.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagingVO.Criteria;
import pagingVO.PageVO;


@Slf4j //logging
@AllArgsConstructor
@RestController
@RequestMapping("/restful/*")
public class Controller {
	
	
	@Autowired
	private BoardService service;
	
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mav) {
		mav.setViewName("home");
		return mav;
	}
	
	@GetMapping("/loginForm")
	public ModelAndView loginForm(ModelAndView mav) {
		log.info("welcome login form!");
		mav.setViewName("loginForm2");
		return mav;
		
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("home");
		return mav;
	}

	
	//리스트
	@GetMapping("/list")
	public ModelAndView name(ModelAndView mav,Criteria cri) {
		log.debug("list()....");
		log.info("list controller");
		mav.setViewName("sd_admin/tables");
		mav.addObject("list", service.getlist(cri));
		
		int total = service.getTotal(cri);
		log.info("total");
		mav.addObject("pageMaker", new PageVO(cri,total));
		return mav;
	}
	
	//contenteView
	@GetMapping("/board/{bId}")
	public ModelAndView contentView(ModelAndView mav, BoardVO vo) {
		mav.setViewName("content_view");
		mav.addObject("content_view", service.getBoard(vo.getbId()));
		return mav;
	}
	
	//delete
	@DeleteMapping("/board/{bId}")
	public ResponseEntity<String> delete(BoardVO vo) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.delete(vo.getbId());
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//수정
	@PutMapping("/modify/{bId}")
	public ResponseEntity<String> modify(@RequestBody BoardVO vo) {
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modify(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//replyView
	@GetMapping("/relpyView/{bId}")
	public ModelAndView replyView(ModelAndView mav, BoardVO vo) {
		mav.setViewName("reply_view");
		mav.addObject("reply_view", service.replyView(vo.getbId()));
		
		return mav;
		
	}
	
	//reply
	@PostMapping("/reply/{bId}")
	public ResponseEntity<String> reply(@RequestBody BoardVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.reply(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//writeView
	@GetMapping("/writeView")
	public ModelAndView writeView(ModelAndView mav) {
		 mav.setViewName("write_view");
		
		return mav;
		
	}
	
	//write
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestBody BoardVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.write(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
	
	
	
	
}
