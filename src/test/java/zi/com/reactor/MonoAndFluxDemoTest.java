package zi.com.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoAndFluxDemoTest {

    private int a = 1000;

    @Test
    public void testJustDeferFromCallable(){

        Mono monoJust = Mono.just(generate("monoJust"));
        Mono monoFromCallable = Mono.fromCallable(() -> generate("monoFromCallable"));
        Mono monoDefer = Mono.defer(() -> Mono.just(generate("monoDefer")));

        System.out.println("-------------CHANGING THE VALUE OF THE ELEMENT IN MONO FROM 1000 TO 2000 ---------------");
        a = 2000;

        System.out.println("++++++++++++++++++++1st SUBSCREIBING++++++++++++++++++++");
        monoJust.subscribe(v -> System.out.println("monoJust = " + v));
        monoFromCallable.subscribe(v -> System.out.println("monoFromCallable = " + v));
        monoDefer.subscribe(v -> System.out.println("monoDefer = " + v));

        System.out.println("-------------CHANGING THE VALUE OF THE ELEMENT IN MONO FROM 2000 TO 3000 ---------------");
        a = 3000;

        System.out.println("++++++++++++++++++++2nd SUBSCREIBING++++++++++++++++++++");
        monoJust.subscribe(v -> System.out.println("monoJust = " + v));
        monoFromCallable.subscribe(v -> System.out.println("monoFromCallable = " + v));
        monoDefer.subscribe(v -> System.out.println("monoDefer = " + v));
    }

    private int generate(String myMono){
        System.out.println("[Calling gererate method by " + myMono + "]");
        return a;
    }
}
