package com.xs.youtu.common;

public class HandTrackEnum {
	public static String getLabelName(String label) {
		String labelName ="";
		switch (label) {
		case "1":
			labelName="1";
			return labelName;
		case "6":
			labelName="6";
			return labelName;
		case "8":
			labelName="8";
			return labelName;
		case "FIST":
			labelName="拳头";
			return labelName;
		case "HEART":
			labelName="比心";
			return labelName;
		case "LIFT":
			labelName="托";
			return labelName;
		case "LIKE":
			labelName="点赞";
			return labelName;
		case "LOVE":
			labelName="我爱你";
			return labelName;
		case "OK":
			labelName="ok";
			return labelName;
		case "PAPER":
			labelName="布";
			return labelName;
		case "ROCK":
			labelName="摇滚手势";
			return labelName;
		case "SCISSOR":
			labelName="剪刀";
			return labelName;
		default:
		   labelName =label;
		   return labelName;
		}
	}
	
}
