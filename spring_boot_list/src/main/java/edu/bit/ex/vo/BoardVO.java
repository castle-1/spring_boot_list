package edu.bit.ex.vo;

import java.sql.Timestamp;

public class BoardVO {
	  private int bId;
	  private String nName;
	  private String bTitle;
	  private String bContent;
	  private Timestamp bDate;
	  private int bHit;
	  private int bGroup;
	  private int bStep;
	  private int bIndent;
	   
	   public BoardVO() {
	      // TODO Auto-generated constructor stub
	   }
	   
	   public BoardVO(int bId, String nName, String bTitle, String bContent, Timestamp bDate, int bHit, int bGroup, int bStep, int bIndent) {
	      // TODO Auto-generated constructor stub
	      this.bId = bId;
	      this.nName = nName;
	      this.bTitle = bTitle;
	      this.bContent = bContent;
	      this.bDate = bDate;
	      this.bHit = bHit;
	      this.bGroup = bGroup;
	      this.bStep = bStep;
	      this.bIndent = bIndent;
	   }

	   public int getbId() {
	      return bId;
	   }

	   public void setbId(int bId) {
	      this.bId = bId;
	   }

	   public String getnName() {
	      return nName;
	   }

	   public void setnName(String nName) {
	      this.nName = nName;
	   }

	   public String getbTitle() {
	      return bTitle;
	   }

	   public void setbTitle(String bTitle) {
	      this.bTitle = bTitle;
	   }

	   public String getbContent() {
	      return bContent;
	   }

	   public void setbContent(String bContent) {
	      this.bContent = bContent;
	   }

	   public Timestamp getbDate() {
	      return bDate;
	   }

	   public void setbDate(Timestamp bDate) {
	      this.bDate = bDate;
	   }

	   public int getbHit() {
	      return bHit;
	   }

	   public void setbHit(int bHit) {
	      this.bHit = bHit;
	   }

	   public int getbGroup() {
	      return bGroup;
	   }

	   public void setbGroup(int bGroup) {
	      this.bGroup = bGroup;
	   }

	   public int getbStep() {
	      return bStep;
	   }

	   public void setbStep(int bStep) {
	      this.bStep = bStep;
	   }

	   public int getbIndent() {
	      return bIndent;
	   }

	   public void setbIndent(int bIndent) {
	      this.bIndent = bIndent;
	   }
	   
	}