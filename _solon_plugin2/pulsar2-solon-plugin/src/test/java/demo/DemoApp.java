package demo;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;

import io.github.majusko.pulsar2.solon.EnablePulsar2;

/**
 * @author noear 2022/2/24 created
 */
@EnablePulsar2
public class DemoApp {
    public static void main(String[] args){
        Solon.start(DemoApp.class, args);
    }
}
