package com.example.imersaojava.service;

import com.example.imersaojava.StickerRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static javax.imageio.ImageIO.read;

public class StickerService {

    private static final String PNG_FORMAT = "png";
    private static final String DIRECTORY = "stickers/";

    public static void makeStickers(String posterUrl, String fileName, String text, Color color, Font font) throws Exception {
        InputStream inputStream = new URL(posterUrl).openStream();

        BufferedImage originalImage = read(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        graphics.setColor(color);
        graphics.setFont(font);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
        int widthText = (int) rectangle.getWidth();
        int textPosition = (width - widthText) / 2;

        graphics.drawString(text, textPosition, newHeight - 75);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(text, font, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform affineTransform = graphics.getTransform();
        affineTransform.translate(textPosition, newHeight - 75);
        graphics.setTransform(affineTransform);

        var stroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(stroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        var directory = new File(DIRECTORY);
        directory.mkdir();

        ImageIO.write(newImage, PNG_FORMAT, new File(fileName));
    }

    public static void makeStickersForTopMovies(List<StickerRequest> stickerRequestList) throws Exception {
        for (StickerRequest stickerRequest : stickerRequestList) {
            makeStickers(stickerRequest.getPoster(), stickerRequest.getFileName(), stickerRequest.getText(), stickerRequest.getColor(), stickerRequest.getFont());
        }
    }

}
