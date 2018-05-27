package com.flashypenguinz.ZombieSurvival.helpers;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;

public class Artist {

	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(GameConstants.DISPLAY_WIDTH,
					GameConstants.DISPLAY_HEIGHT));
			Display.setTitle(GameConstants.DISPLAY_TITLE);
			Display.setIcon(load(GameConstants.TEXTURES_FOLDER+"icon.png"));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glEnable(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GameConstants.DISPLAY_WIDTH, GameConstants.DISPLAY_HEIGHT,
				0, -1, 1);
		glEnable(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void updateDisplay() {
		Display.sync(GameConstants.DISPLAY_FPS);
		Display.update();
	}

	public static void destroyDisplay() {
		Display.destroy();
	}
	
	public static void drawColoredQuad(float x, float y, float width,
			float height, float rotation, float r, float g, float b, float a) {
		glDisable(GL_TEXTURE_2D);
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glColor4f(r, g, b, a);
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(width, 0);
		glVertex2f(width, height);
		glVertex2f(0, height);
		glEnd();
		glColor4f(1, 1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void drawColoredQuad(float x, float y, float width,
			float height, float rotation, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glColor3f(r, g, b);
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(width, 0);
		glVertex2f(width, height);
		glVertex2f(0, height);
		glEnd();
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	}

	public static void drawTexturedQuad(float x, float y, float width, float height, Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 0);
		glVertex2f(x + width, y);
		glTexCoord2f(1, 1);
		glVertex2f(x + width, y + height);
		glTexCoord2f(0, 1);
		glVertex2f(x, y + height);
		glEnd();
	}
	
	public static void drawTexturedQuad(float x, float y, float width,
			float height, Texture texture, float texXMin, float texYMin,
			float texXMax, float texYMax) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(texXMin, texYMin);
		glVertex2f(x, y);
		glTexCoord2f(texXMax, texYMin);
		glVertex2f(x + width, y);
		glTexCoord2f(texXMax, texYMax);
		glVertex2f(x + width, y + height);
		glTexCoord2f(texXMin, texYMax);
		glVertex2f(x, y + height);
		glEnd();
	}

	public static void drawTexturedQuad(float x, float y, float width,
			float height, float rotation, Texture texture, float texXMin,
			float texYMin, float texXMax, float texYMax) {
		texture.bind();
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glBegin(GL_QUADS);
		glTexCoord2f(texXMin, texYMin);
		glVertex2f(-width/2, -height/2);
		glTexCoord2f(texXMax, texYMin);
		glVertex2f(width/2, -height/2);
		glTexCoord2f(texXMax, texYMax);
		glVertex2f(width/2, height/2);
		glTexCoord2f(texXMin, texYMax);
		glVertex2f(-width/2, height/2);
		glEnd();
	}
 
    public static ByteBuffer[] load(String path) {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ByteBuffer[] buffers = null;
        String OS = System.getProperty("os.name").toUpperCase();
        if(OS.contains("WIN")) {
            buffers = new ByteBuffer[2];
            buffers[0] = loadInstance(image, 16);
            buffers[1] = loadInstance(image, 32);
        } else if(OS.contains("MAC")) {
            buffers = new ByteBuffer[1];
            buffers[0] = loadInstance(image, 128);
        } else {
            buffers = new ByteBuffer[1];
            buffers[0] = loadInstance(image, 32);
        }
        return buffers;
    }
 
    private static ByteBuffer loadInstance(BufferedImage image, int dimension) {
        BufferedImage scaledIcon = new BufferedImage(dimension, dimension,
                BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D g = scaledIcon.createGraphics();
        double ratio = getIconRatio(image, scaledIcon);
        double width = image.getWidth() * ratio;
        double height = image.getHeight() * ratio;
        g.drawImage(image, (int) ((scaledIcon.getWidth() - width) / 2),
                (int) ((scaledIcon.getHeight() - height) / 2), (int) (width),
                (int) (height), null);
        g.dispose();
 
        return convertToByteBuffer(scaledIcon);
    }
 

    private static double getIconRatio(BufferedImage src, BufferedImage icon) {
        double ratio = 1;
        if (src.getWidth() > icon.getWidth())
            ratio = (double) (icon.getWidth()) / src.getWidth();
        else
            ratio = (int) (icon.getWidth() / src.getWidth());
        if (src.getHeight() > icon.getHeight())
        {
            double r2 = (double) (icon.getHeight()) / src.getHeight();
            if (r2 < ratio)
                ratio = r2;
        }
        else
        {
            double r2 = (int) (icon.getHeight() / src.getHeight());
            if (r2 < ratio)
                ratio = r2;
        }
        return ratio;
    }
 
    public static ByteBuffer convertToByteBuffer(BufferedImage image) {
        byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
        int counter = 0;
        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++) {
                int colorSpace = image.getRGB(j, i);
                buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
                buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
                buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
                buffer[counter + 3] = (byte) (colorSpace >> 24);
                counter += 4;
            }
        return ByteBuffer.wrap(buffer);
    }

}
