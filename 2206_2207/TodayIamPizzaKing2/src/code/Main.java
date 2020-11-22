package code;

public class Main {
	public static Music introMusic;
	public static void main(String[] args) {
		new Game();
		introMusic = new Music("introMusic.mp3",true);;
        introMusic.start();
	}
}
