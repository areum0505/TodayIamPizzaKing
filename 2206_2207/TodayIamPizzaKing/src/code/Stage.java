package code;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Stage {	
	private Image stageBackground;																		// 각 스테이지마다 배경화면
	private Image stage1Back = new ImageIcon("images/stage/stage1Back.png").getImage();
	private Image stage2Back = new ImageIcon("images/stage/stage2Back.png").getImage();
	private Image stage3Back = new ImageIcon("images/stage/stage3Back.png").getImage();
	private Image stage4Back = new ImageIcon("images/stage/stage4Back.png").getImage();
	private Image stage5Back = new ImageIcon("images/stage/stage5Back.png").getImage();
	private Image stage6Back = new ImageIcon("images/stage/stage6Back.png").getImage();
	
	private Image stageExplain;
	private Image stage1Ex = new ImageIcon("images/stage/stage1Explain.png").getImage();
	
	private String stageName;				// 스테이지 이름 
	
	private boolean isClear = false;	// 클리어 여부
	
	public Stage(String stageName) {
		super();
		this.stageName = stageName;
		
		switch(stageName) {
		case "치즈": 
			stageBackground = stage1Back; 
			stageExplain = stage1Ex;
			break;
		case "양파": stageBackground = stage2Back; break;
		case "버섯": stageBackground = stage3Back; break;
		case "페퍼로니": stageBackground = stage4Back; break;
		case "피망": stageBackground = stage5Back; break;
		case "소스": stageBackground = stage6Back; break;
		}
	}
	public Image getStageBackground() {
		return stageBackground;
	}
	public void setStageBackground(Image stageBackground) {
		this.stageBackground = stageBackground;
	}
	public Image getStageExplain() {
		return stageExplain;
	}
	public void setStageExplain(Image stageExplain) {
		this.stageExplain = stageExplain;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public boolean isClear() {
		return isClear;
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
}
