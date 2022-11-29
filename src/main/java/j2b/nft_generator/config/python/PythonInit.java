package j2b.nft_generator.config.python;

import j2b.nft_generator.bash.BashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * 실제 서버에서만 적용되는, 빌드 시 파이썬 관련 패키지 설치를 진행하는 클래스입니다.
 * @version 1.0.2
 * @author CHO Min Ho
 */
@Component
@RequiredArgsConstructor
@Profile("prod")
@Slf4j
public class PythonInit {

    private final BashService bashService;

    /**
     * 빌드 시 파이썬 설치 명령어
     */
    private final List<List<String>> installCommandList = Arrays.asList(
            Arrays.asList("/bin/sh", "-c", "sudo yum install -y python-pip"),
            Arrays.asList("/bin/sh", "-c", "sudo pip3 install numpy"),
            Arrays.asList("/bin/sh", "-c", "sudo yum install -y opencv-python"),
            Arrays.asList("/bin/sh", "-c", "sudo pip3 install -y opencv-python"));

    /**
     * 실 서버에 배포 시 파이썬 관련 패키지를 설치하는 bash 명령어를 실행합니다.
     */
    @PostConstruct
    public void init() {
        for (List<String> command : installCommandList) {
            bashService.executeCommand(command);
        }
    }
}
