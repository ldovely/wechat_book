package ls.eclair.wechat_book.entity;

/**
 * @author lisbo
 * @description
 * @since 2017-12-7 15:21
 */


public class VoiceMessage extends Voice {

    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
