/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.lostandfound.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>
 * Title: SaveCodeService
 * </p>
 * <p>
 * Description: ������֤��ķ������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class SaveCode {

	/**
	 * ������֤����ַ�Ԫ��
	 * */
	private char[] elements;
	/**
	 * ��֤��ͼƬ���
	 * */
	private int width;
	/**
	 * ��֤��ͼƬ�߶�
	 * */
	private int height;
	/**
	 * ��֤���ַ�������
	 * */
	private int length;
	/**
	 * ��֤�뻺��ͼƬ
	 * */
	private BufferedImage image;

	/**
	 * ��֤�����ַ���
	 * */
	private String codeString;

	/**
	 * ��֤������������
	 * 
	 * @param elements
	 *            ������֤����ַ�Ԫ��
	 * @param width
	 *            ��֤��ͼƬ���
	 * @param height
	 *            ��֤��ͼƬ�߶�
	 * @param length
	 *            ��֤���ַ�������
	 * */
	public SaveCode(char[] elements, int width, int height, int length) {
		// ��ʼ��������֤����ַ�Ԫ������
		this.elements = elements;
		// ��ʼ����֤��ͼƬ���
		this.width = width;
		// ��ʼ����֤��ͼƬ�߶�
		this.height = height;
		// ��ʼ����֤���ַ�������
		this.length = length;
	}

	/**
	 * ������֤��ͼƬ�ķ���
	 * */
	public void createSaveCodeImage() {
		// ������֤�뻺��ͼƬ
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		// ���������
		Random random = new Random();
		// �趨����ɫ
		g.setColor(getRandColor(220, 250));
		// ����ʵ�ľ�������
		g.fillRect(0, 0, width, height);
		// �趨����
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// ���߿�
		// g.drawRect(0,0,width-1,height-1);
		g.draw3DRect(0, 0, width - 1, height - 1, true);
		
		// ѭ������������
		for (int i = 0; i < 155; i++) {
			// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
			g.setColor(getRandColor(160, 200));
			// ���������X����
			int x = random.nextInt(width);
			// ���������Y����
			int y = random.nextInt(height);
			// �������յ�X����
			int xl = random.nextInt(12);
			// �������յ�Y����
			int yl = random.nextInt(12);
			// ���Ƹ�����
			g.drawLine(x, y, x + xl, y + yl);
		}
		// ȡ�����������֤��(6λ����)
		codeString = "";
		// ѭ����ȡ����ַ�
		for (int i = 0; i < length; i++) {
			// ��ȡ����ַ�
			char rand = elements[Math.abs(random.nextInt()) % elements.length];
			// ��������ַ���
			codeString += rand;
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
			g.drawString(String.valueOf(rand), 13 * i + 6, 16);
		}
		// g.drawOval(0, 12, 60, 11);
		// ���ͼƬ
		g.dispose();

	}

	/**
	 * ��ȡ��֤��ͼƬ�ķ���
	 * 
	 * @return �����õ���֤��ͼƬ
	 * */
	public BufferedImage getImage() {
		// ������֤��ͼƬ
		return image;
	}

	/**
	 * ��ȡ��֤���ַ����ķ���
	 * 
	 * @return ���ɵ���֤���ַ���
	 * */
	public String getCodeString() {
		// ������֤���ַ���
		return codeString;
	}

	/**
	 * ������ɸ�������ɫ�ķ���
	 * 
	 * @param fc
	 *            ����ɫϵֵ
	 * @param bc
	 *            ɫϵƫ����ֵ
	 * @return �����õ������ɫ
	 * */
	private Color getRandColor(int fc, int bc) {
		// �����������
		Random random = new Random();
		// ����ɫϵֵ����
		if (fc > 255)
			// �޶�����ɫϵֵ���ֵ
			fc = 255;
		// ��ɫƫ����ֵ����
		if (bc > 255)
			// �޶���ɫƫ����ֵ���ֵ
			bc = 255;
		// ���ɵ������ɫ����ֵ
		int r = fc + random.nextInt(bc - fc);
		// ���ɵ������ɫ����ֵ
		int g = fc + random.nextInt(bc - fc);
		// ���ɵ������ɫ����ֵ
		int b = fc + random.nextInt(bc - fc);
		// �����������ɫ����
		return new Color(r, g, b);
	}

}
