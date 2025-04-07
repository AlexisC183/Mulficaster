package com.alexisc183.shared;

public class JLoadingMessage {
	public enum LoadingStyle {
		PERIODS,
		LINES,
		ROTARY_LINE
	}
	
	private StringSetter stringSetter;
	private String startMessage, stopMessage, spacing;
	private LoadingStyle loadingStyle;
	private Thread thread;
	private boolean hasStopMessage;
	
	public JLoadingMessage(String message, StringSetter stringSetter) {
		if (message == null) {
			startMessage = spacing = "";
		}
		else {
			spacing = " ";
			startMessage = message;
		}
		
		if (stringSetter == null) {
			throw new IllegalArgumentException("Passing \"null\" is not allowed here");
		}
		
		this.stringSetter = stringSetter;
		loadingStyle = LoadingStyle.PERIODS;
	}
	
	public JLoadingMessage(String startMessage, StringSetter stringSetter, String stopMessage) {
		this(startMessage, stringSetter);
		
		this.stopMessage = stopMessage;
		hasStopMessage = true;
	}
	
	public LoadingStyle getLoadingStyle() {
		return loadingStyle;
	}
	
	public void setLoadingStyle(LoadingStyle loadingStyle) throws IllegalArgumentException {
		if (loadingStyle == null) {
			throw new IllegalArgumentException("Passing \"null\" is not allowed here");
		}
		this.loadingStyle = loadingStyle;
	}
	
	private void rotaryLineAction() {
		while (!Thread.interrupted()) {
			try {
				stringSetter.set(startMessage + spacing + '\u2500');
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + '\\');
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + '|');
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + '/');
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void linesAction() {
		while (!Thread.interrupted()) {
			try {
				stringSetter.set(startMessage + spacing + "___");
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + "─__");
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + "_─_");
				Thread.sleep(1000);
				stringSetter.set(startMessage + spacing + "__─");
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void periodsAction() {
		while (!Thread.interrupted()) {
			try {
				stringSetter.set(startMessage + "   ");
				Thread.sleep(1000);
				stringSetter.set(startMessage + ".  ");
				Thread.sleep(1000);
				stringSetter.set(startMessage + ".. ");
				Thread.sleep(1000);
				stringSetter.set(startMessage + "...");
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void start() {
		switch (loadingStyle) {
			case ROTARY_LINE:
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						rotaryLineAction();
					}
				});
				break;
			case LINES:
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						linesAction();
					}
				});
				break;
			default:
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						periodsAction();
					}
				});
				break;
		}
		
		thread.start();
	}
	
	public void stop() {
		thread.interrupt();
		
		if (hasStopMessage) {
			stringSetter.set(stopMessage);
		}
	}
}
