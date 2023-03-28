package com.example.imersaojava;

import com.example.imersaojava.service.MoviesService;
import com.example.imersaojava.service.StickerService;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.imersaojava.service.MoviesService.getPosterMovies;

public class Application {

    private static final String DIRECTORY = "stickers/";

    public static void main(String[] args) throws Exception {
        MoviesService.findMostPopularMovies();

        List<StickerRequest> stickerRequestList = createStickerRequestList();

        StickerService.makeStickersForTopMovies(stickerRequestList);
    }
    
    private static List<StickerRequest> createStickerRequestList() throws IOException, InterruptedException {
        List<StickerRequest> stickerRequestList = new ArrayList<>();

        List<String> posterMovies = getPosterMovies();

        Font font = new Font("Impact", Font.ITALIC, 150);

        for (int x = 0; x < posterMovies.size(); x++) {
            StickerRequest stickerRequest = new StickerRequest();
            stickerRequest.setPoster(posterMovies.get(x));
            stickerRequest.setColor(Color.PINK);
            stickerRequest.setText("Giovana indica!");
            stickerRequest.setFileName(DIRECTORY + "sticker_" + x + ".png");
            stickerRequest.setFont(font);

            stickerRequestList.add(stickerRequest);
        }

        return stickerRequestList;
    }
}