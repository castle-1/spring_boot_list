package edu.bit.ex;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

@Slf4j // logging
@AllArgsConstructor
@RestController
public class Controller {

	@Autowired
	private BoardService service;

	@GetMapping("/")
	public ModelAndView home(ModelAndView mav) {
		mav.setViewName("sd_admin/index");
		return mav;
	}

	@GetMapping("/loginForm")
	public ModelAndView loginForm(ModelAndView mav) {
		mav.setViewName("sd_admin/login");
		return mav;
	}

	@RequestMapping("/successLogin")
	public ModelAndView sLogin(ModelAndView mav) {
		mav.setViewName("sd_admin/index");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mav) {
		mav.setViewName("sd_admin/index");
		return mav;
	}

	// 리스트
	@GetMapping("/admin/list")
	public ModelAndView name(ModelAndView mav, Criteria cri) {
		log.debug("list()....");
		log.info("list controller");
		mav.setViewName("sd_admin/tables");
		mav.addObject("list", service.getlist(cri));

		int total = service.getTotal(cri);
		log.info("total");
		mav.addObject("pageMaker", new PageVO(cri, total));
		return mav;
	}

	// contenteView
	@GetMapping("/admin/board/{bId}")
	public ModelAndView contentView(ModelAndView mav, BoardVO vo) {
		mav.setViewName("content_view");
		mav.addObject("content_view", service.getBoard(vo.getbId()));
		return mav;
	}

	// delete
	@DeleteMapping("/admin/board/{bId}")
	public ResponseEntity<String> delete(BoardVO vo) {

		ResponseEntity<String> entity = null;

		try {
			service.delete(vo.getbId());
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	// 수정
	@PutMapping("/admin/modify/{bId}")
	public ResponseEntity<String> modify(@RequestBody BoardVO vo) {

		ResponseEntity<String> entity = null;

		try {
			service.modify(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// replyView
	@GetMapping("/admin/relpyView/{bId}")
	public ModelAndView replyView(ModelAndView mav, BoardVO vo) {
		mav.setViewName("reply_view");
		mav.addObject("reply_view", service.replyView(vo.getbId()));

		return mav;

	}

	// reply
	@PostMapping("/admin/reply/{bId}")
	public ResponseEntity<String> reply(@RequestBody BoardVO vo) {
		ResponseEntity<String> entity = null;

		try {
			service.reply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// writeView
	@GetMapping("/admin/writeView")
	public ModelAndView writeView(ModelAndView mav) {
		mav.setViewName("write_view");

		return mav;

	}

	// write
	@PostMapping("/admin/write")
	public ResponseEntity<String> write(@RequestBody BoardVO vo) {
		ResponseEntity<String> entity = null;

		try {
			service.write(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;

	}

}
