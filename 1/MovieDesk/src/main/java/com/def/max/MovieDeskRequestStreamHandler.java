package main.java.com.def.max;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class MovieDeskRequestStreamHandler extends SpeechletRequestStreamHandler
{
    private static final Set<String> supportedApplicationIds;

    static
    {
        supportedApplicationIds = new HashSet<>();

        supportedApplicationIds.add("amzn1.ask.skill.df7b4340-2467-4846-b66e-fc240e37a0f5");
    }

    public MovieDeskRequestStreamHandler()
    {
        super(new GetMovieDetailsSpeechLet(), supportedApplicationIds);
    }
}
