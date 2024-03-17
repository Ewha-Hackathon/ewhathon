package ewha_hackathon.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Hashtag {
    신나는, 잔잔한, 힐링, 자극적, 비판적, 감각적, 우아한, 유익한, 창의적, 혼자도_환영, 볼거리_많은, 친구와_함께, 연합_이벤트, 친구_만들기, 홍보_행사, 현장예매_가능, 시즌_이벤트, 외부인_환영;

    public static List<Hashtag> parseKeywords(List<String> keywords) {
        return keywords.stream()
                .map(Hashtag::valueOf)
                .collect(Collectors.toList());
    }

}
