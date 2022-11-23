package j2b.nft_generator.config.python;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 실제 서버에서만 적용되는, 빌드 시 파이썬 관련 패키지 설치를 진행하는 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Component
@RequiredArgsConstructor
@Profile("prod")
public class PythonInit {

    private final String BASH_PREFIX = "/bin/sh/ -c ";

    /**
     * 빌드 시 파이썬 설치 명령어
     */
    private final List<List<String>> installCommandList = Arrays.asList(
            Arrays.asList("sudo", "yum", "install", "-y", "python-pip"),
            Arrays.asList("sudo", "pip3", "install", "numpy"),
            Arrays.asList("sudo", "yum", "install", "-y", "opencv-python"),
            Arrays.asList("sudo", "pip3", "install", "opencv-python"));
            /*BASH_PREFIX + "sudo yum install -y python-pip",
            BASH_PREFIX + "sudo pip3 install numpy",
            BASH_PREFIX + "sudo yum install -y opencv-python",
            BASH_PREFIX + "sudo pip3 install opencv-python");*/

    /**
     * 실 서버에 배포 시 파이썬 관련 패키지를 설치하는 bash 명령어를 실행합니다.
     */
    @PostConstruct
    public void init() {
        for (List<String> command : installCommandList) {
            executeCommand(command);
        }
    }

    /**
     * Bash 명령어를 실행하는 메서드입니다.
     * @param command 명령어
     */
    private void executeCommand(List<String> command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
